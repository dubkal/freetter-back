package dev.dubkal.freetter.service;

import dev.dubkal.freetter.dto.SignupDto;
import dev.dubkal.freetter.model.User;
import dev.dubkal.freetter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    final private PasswordEncoder encoder;

    AuthService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    private final UserRepository userRepository;

    public User registerUser(SignupDto signupDto) {
        ModelMapper modelMapper = new ModelMapper();
        signupDto.setPassword(encoder.encode(signupDto.getPassword()));
        return userRepository.save(modelMapper.map(signupDto,User.class));
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
