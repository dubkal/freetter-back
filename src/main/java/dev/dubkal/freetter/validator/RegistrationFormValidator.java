package dev.dubkal.freetter.validator;

import dev.dubkal.freetter.dto.RegistrationDto;
import dev.dubkal.freetter.repository.UserRepository;
import dev.dubkal.freetter.service.AuthService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrationFormValidator implements Validator {

    private final AuthService authService;
    public RegistrationFormValidator(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationDto registrationDto = (RegistrationDto) target;

        if(registrationDto.getUsername() == null || registrationDto.getUsername().length() < 6){
            errors.rejectValue("username", "registration.username.short","input too short");
        }
        if(!registrationDto.getUsername().matches("\\p{Alnum}*")){
            errors.rejectValue("username","registration.username.allowed","only letters and numbers allowed");
        }
        if(authService.usernameExists(registrationDto.getUsername())){
            errors.rejectValue("username","registration.username.exists","username taken");
        }
        if(registrationDto.getFirstName() == null || registrationDto.getFirstName().strip().equals("")){
            errors.rejectValue("firstName", "registration.firstName.empty","input empty!");
        }
        if(registrationDto.getLastName() == null || registrationDto.getLastName().strip().equals("")){
            errors.rejectValue("lastName", "registration.lastName.empty","input empty!");
        }
        if(registrationDto.getPassword() == null || registrationDto.getPassword().length() < 6){
            errors.rejectValue("password", "registration.password.short", "input too short");
        }
    }
}
