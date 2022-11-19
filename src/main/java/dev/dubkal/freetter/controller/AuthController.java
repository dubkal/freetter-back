package dev.dubkal.freetter.Controller;

import dev.dubkal.freetter.Model.User;
import dev.dubkal.freetter.Repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserRepository userRepository;

    AuthController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
}
