package com.utp.algoritmos.mymclothing.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.utp.algoritmos.mymclothing.models.Categoria;
import com.utp.algoritmos.mymclothing.models.Producto;
import com.utp.algoritmos.mymclothing.models.ProductoDto;
import com.utp.algoritmos.mymclothing.models.Usuario;
import com.utp.algoritmos.mymclothing.services.CategoriaService;
import com.utp.algoritmos.mymclothing.services.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/productos")
    public String productos(Model model, HttpSession session) {
        if(!isAdmin(session)){
            return "redirect:/";
        }
        List<Producto> productos = productoService.findAll();
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("productos", productos.reversed());
        model.addAttribute("categorias", categorias);
        return "admin/productos"; 
    }
    @PostMapping("/productos")
    public String registrarProducto(@ModelAttribute ProductoDto productoDto, HttpSession session) {
        try{
            MultipartFile img = productoDto.getImagen();
            Producto producto = new Producto();
            if(!img.isEmpty()){
                byte[] bytes = img.getBytes();
                String fileName = (LocalDateTime.now().toString() +"-"+ img.getOriginalFilename()).replace(":", "-");
                Path path = Paths.get("src/main/resources/static/imgProductos/"+fileName);
                Files.write(path, bytes);
                producto.setUrlImagen(fileName);
            }

            producto.setId(productoDto.getId());
            producto.setNombreProducto(productoDto.getNombreProducto());
            producto.setCategoria(new Categoria(productoDto.getCategoria()));
            producto.setDescripcion(productoDto.getDescripcion());
            producto.setPrecioProduccion(productoDto.getPrecioProduccion());
            producto.setPrecioVenta(productoDto.getPrecioVenta());
            producto.setStock(productoDto.getStock());
            producto.setTalla(productoDto.getTalla());
            productoService.save(producto);

        }catch(IOException e){
            e.printStackTrace();
        }
        return "redirect:/admin/productos"; 
    }

    @DeleteMapping("/productos/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.delete(id);
        return "redirect:/admin/productos"; 
    }


    private boolean isAdmin(HttpSession session){
        Usuario sessionUser = (Usuario) session.getAttribute("sesion");
        if (sessionUser != null) {
            if(sessionUser.getRol() == 1){
                return true;
            }
            return false;
        }
        return false;
    }
}
