package dev.dubkal.freetter.controller;

import dev.dubkal.freetter.dto.MessageResponse;
import dev.dubkal.freetter.dto.UserDto;
import dev.dubkal.freetter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public MessageResponse allAccess(){
        return new MessageResponse("Server is up");
    }

    @GetMapping("/username")
    @PreAuthorize("isAuthenticated()")
    public MessageResponse getUsername(Authentication authentication){
        return new MessageResponse(authentication.getName());
    }

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public UserDto getUserData(Authentication authentication){
        return userService.getUserDto(authentication.getName());
    }
}
