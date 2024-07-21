package com.utp.algoritmos.mymclothing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.utp.algoritmos.mymclothing.models.Producto;
import com.utp.algoritmos.mymclothing.models.Usuario;
import com.utp.algoritmos.mymclothing.services.ProductoService;
import com.utp.algoritmos.mymclothing.services.UsuarioService;

@Controller
public class ViewController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "index"; // Referencia a src/main/resources/templates/index.html
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Usuario usuario, Model model) {
        usuarioService.save(usuario);
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Usuario usuario, Model model) {
        usuarioService.login(usuario);
        return "index";
    }
}

