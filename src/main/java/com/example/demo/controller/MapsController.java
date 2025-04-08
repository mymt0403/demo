package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MapsController {
    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("apiKey", apiKey);
        System.out.println(apiKey);
        return "index";
    }
}
