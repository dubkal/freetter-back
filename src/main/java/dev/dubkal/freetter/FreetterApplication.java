package dev.dubkal.freetter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"dev.dubkal.freetter.repository", "dev.dubkal.freetter.model"})
public class FreetterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreetterApplication.class, args);
    }

}