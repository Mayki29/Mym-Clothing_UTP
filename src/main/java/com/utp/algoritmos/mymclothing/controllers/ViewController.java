package com.utp.algoritmos.mymclothing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.utp.algoritmos.mymclothing.models.Producto;
import com.utp.algoritmos.mymclothing.models.Usuario;
import com.utp.algoritmos.mymclothing.services.ProductoService;
import com.utp.algoritmos.mymclothing.services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
// @SessionAttributes("sesion")
public class ViewController {

    @Value("${stripe.api.publicKey}")
    private String publicKey;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);

        //Obtener sesion
        Usuario sessionUser = (Usuario) session.getAttribute("sesion");
        if (sessionUser != null) {
            model.addAttribute("sesion", sessionUser);
        }

        if (model.containsAttribute("message")) {
            String message = (String) model.getAttribute("message");
            System.out.println("Mensaje en vista: " + message);
        }
        return "index"; // Referencia a src/main/resources/templates/index.html
    }
}

