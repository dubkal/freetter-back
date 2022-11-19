package dev.dubkal.freetter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/api/home")
    public String home() {

        return "Freetter";
    }
}