package dev.dubkal.freetter.controller;

import dev.dubkal.freetter.model.User;
import dev.dubkal.freetter.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserRepository userRepository;

    AuthController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/api/auth/register")
    public User register(@RequestBody User newUser){
        logger.info("User is: " + newUser.toString());
        return userRepository.save(newUser);
    }
}
