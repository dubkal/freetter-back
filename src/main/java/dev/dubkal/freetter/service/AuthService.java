package dev.dubkal.freetter.service;

import dev.dubkal.freetter.dto.RegistrationDto;
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


    public RegistrationDto registerUser(RegistrationDto registrationDto) {
        User savedUser = userRepository.save(modelMapper.map(registrationDto, User.class));
        return modelMapper.map(savedUser, RegistrationDto.class);
    }

    public boolean usernameExists(String username) {
        return userRepository.countUserByUsername(username) != 0;
    }
}
