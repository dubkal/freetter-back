package dev.dubkal.freetter.service;

import dev.dubkal.freetter.dto.SignupDto;
import dev.dubkal.freetter.model.User;
import dev.dubkal.freetter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();


    public SignupDto registerUser(SignupDto signupDto) {
        User savedUser = userRepository.save(modelMapper.map(signupDto, User.class));
        return modelMapper.map(savedUser, SignupDto.class);
    }

    public boolean usernameExists(String username) {
        return userRepository.countUserByUsername(username) != 0;
    }
}
