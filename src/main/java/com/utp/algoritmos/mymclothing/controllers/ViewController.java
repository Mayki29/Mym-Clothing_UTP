package com.utp.algoritmos.mymclothing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.utp.algoritmos.mymclothing.models.Producto;
import com.utp.algoritmos.mymclothing.services.ProductoService;

@Controller
public class ViewController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String index(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "index"; // Referencia a src/main/resources/templates/index.html
    }
}

