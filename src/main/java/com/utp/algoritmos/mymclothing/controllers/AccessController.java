package com.utp.algoritmos.mymclothing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.utp.algoritmos.mymclothing.models.Usuario;
import com.utp.algoritmos.mymclothing.services.UsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/access")
public class AccessController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        
        Usuario user = usuarioService.login(usuario);
        if(user != null){
            session.setAttribute("sesion", user);
            return "redirect:/"; 
        }
        model.addAttribute("message", "Datos incorrectos");
        return "redirect:/";
    }
    
    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Usuario usuario, Model model) {
        Usuario user = usuarioService.save(usuario);
        model.addAttribute("usuario", user);
        return "redirect:/";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sesion");
        return "redirect:/";
    }

    
}
