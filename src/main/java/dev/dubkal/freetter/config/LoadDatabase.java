package dev.dubkal.freetter.config;

import dev.dubkal.freetter.model.User;
import dev.dubkal.freetter.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            logger.info("Preloading " + userRepository.save(new User("user1", "name1", "last1", "1234")));
        };
    }
}
