package dev.dubkal.freetter.controller;

import dev.dubkal.freetter.dto.RegistrationDto;
import dev.dubkal.freetter.service.AuthService;
import dev.dubkal.freetter.validator.RegistrationFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    AuthController(RegistrationFormValidator registrationFormValidator,
                   AuthService authService,
                   MessageSource messageSource) {
        this.registrationFormValidator = registrationFormValidator;
        this.authService = authService;
        this.messageSource = messageSource;
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final RegistrationFormValidator registrationFormValidator;
    private final MessageSource messageSource;
    private final AuthService authService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationFormValidator);
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<Object> register(@Validated @RequestBody RegistrationDto registrationDto, BindingResult bindingResult) {
        logger.info("User is: " + registrationDto.toString());
        if (bindingResult.hasErrors()) {
            System.out.println("validation error" + registrationDto);
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(objectError -> messageSource.getMessage(Objects.requireNonNull(objectError.getCode()),null,Locale.ENGLISH))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(errors);
        }
        return ResponseEntity.ok(authService.registerUser(registrationDto));
    }
}
