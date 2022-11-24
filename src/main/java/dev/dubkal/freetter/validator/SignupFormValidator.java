package dev.dubkal.freetter.validator;

import dev.dubkal.freetter.dto.SignupDto;
import dev.dubkal.freetter.service.AuthService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignupFormValidator implements Validator {

    private final AuthService authService;
    public SignupFormValidator(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return SignupDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupDto signupDto = (SignupDto) target;

        if(signupDto.getUsername() == null || signupDto.getUsername().length() < 6){
            errors.rejectValue("username", "signup.username.short","input too short");
        }
        if(!signupDto.getUsername().matches("\\p{Alnum}*")){
            errors.rejectValue("username","signup.username.allowed","only letters and numbers allowed");
        }
        if(authService.existsByUsername(signupDto.getUsername())){
            errors.rejectValue("username","signup.username.exists","username taken");
        }
        if(signupDto.getFirstName() == null || signupDto.getFirstName().strip().equals("")){
            errors.rejectValue("firstName", "signup.firstName.empty","input empty!");
        }
        if(signupDto.getLastName() == null || signupDto.getLastName().strip().equals("")){
            errors.rejectValue("lastName", "signup.lastName.empty","input empty!");
        }
        if(signupDto.getPassword() == null || signupDto.getPassword().length() < 6){
            errors.rejectValue("password", "signup.password.short", "input too short");
        }
    }
}
