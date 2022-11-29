package dev.dubkal.freetter.config;

import dev.dubkal.freetter.dto.SignupDto;
import dev.dubkal.freetter.model.User;
import dev.dubkal.freetter.repository.UserRepository;
import dev.dubkal.freetter.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AuthService authService) {
        return args -> {
            logger.info("Preloading " + authService.registerUser(new SignupDto("dubkal", "dubravko", "kaluder", "password")))
            ;
        };
    }
}
