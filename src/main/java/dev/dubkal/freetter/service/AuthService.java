package dev.dubkal.freetter.service;

import dev.dubkal.freetter.dto.SignupDto;
import dev.dubkal.freetter.model.User;
import dev.dubkal.freetter.repository.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
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

    public void registerUser(SignupDto signupDto) {
        ModelMapper modelMapper = new ModelMapper();
        signupDto.setPassword(encoder.encode(signupDto.getPassword()));
        userRepository.save(modelMapper.map(signupDto,User.class));
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
