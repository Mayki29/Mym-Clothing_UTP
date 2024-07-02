package com.utp.algoritmos.mymclothing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index"; // Referencia a src/main/resources/templates/index.html
    }
}

