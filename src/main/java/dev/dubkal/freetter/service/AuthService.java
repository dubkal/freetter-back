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
    ModelMapper modelMapper = new ModelMapper();


//    public void registerUser(SignupDto signupDto) {
//        modelMapper.typeMap(SignupDto.class, User.class)
//                .addMapping(src -> encoder.encode(src.getPassword()), User::setPassword);
//        System.out.println(modelMapper.map(signupDto, User.class));
//        userRepository.save(modelMapper.map(signupDto, User.class));
//    }

    public void registerUser(SignupDto signupDto) {
        //todo:make it use model mapper
        userRepository.save(new User(signupDto.getUsername(),signupDto.getFirstName(),signupDto.getLastName(),encoder.encode(signupDto.getPassword())));
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
