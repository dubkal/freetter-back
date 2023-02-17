package dev.dubkal.freetter.controller;

import dev.dubkal.freetter.dto.JwtResponse;
import dev.dubkal.freetter.dto.LoginDto;
import dev.dubkal.freetter.dto.MessageResponse;
import dev.dubkal.freetter.dto.SignupDto;
import dev.dubkal.freetter.security.UserDetailsImpl;
import dev.dubkal.freetter.security.jwt.JwtUtils;
import dev.dubkal.freetter.service.AuthService;
import dev.dubkal.freetter.validator.SignupFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    AuthController(SignupFormValidator signupFormValidator,
                   AuthService authService,
                   MessageSource messageSource, AuthenticationManager authenticationManager, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.signupFormValidator = signupFormValidator;
        this.authService = authService;
        this.messageSource = messageSource;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final SignupFormValidator signupFormValidator;
    private final MessageSource messageSource;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @InitBinder("signupDto")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(signupFormValidator);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(), loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getFirstName(),
                        userDetails.getLastName()
                )
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Validated @RequestBody SignupDto signupDto, BindingResult bindingResult) {
        logger.info("User signup request: " + signupDto.toString());
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(objectError -> messageSource.getMessage(Objects.requireNonNull(objectError.getCode()), null, Locale.ENGLISH))
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        authService.registerUser(signupDto);
        return ResponseEntity.ok(new MessageResponse("Signup successful."));
    }

}
