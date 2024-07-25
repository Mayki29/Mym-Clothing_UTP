package com.utp.algoritmos.mymclothing.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.utp.algoritmos.mymclothing.models.Producto;
import com.utp.algoritmos.mymclothing.models.Usuario;
import com.utp.algoritmos.mymclothing.models.Venta;
import com.utp.algoritmos.mymclothing.services.ProductoService;
import com.utp.algoritmos.mymclothing.services.UsuarioService;
import com.utp.algoritmos.mymclothing.services.VentaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class HomeControlller {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private VentaService ventaService;

    @GetMapping("/users")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/user/{id}")
    public Usuario getUsuariobyId(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/user")
    public void updateUsuario(@RequestBody Usuario usuario){
        usuarioService.save(usuario);
    }
    
    @DeleteMapping("/user/{id}")
    public void removeUser(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }
    
    @GetMapping("/ventas")
    public List<Venta> getAllVentas(){
        return ventaService.findAll();
    }
    @GetMapping("/ventas/{id}")
    public Venta getByid(@PathVariable Long id){
        return ventaService.findById(id);
    }

    @PostMapping("/ventas")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveVenta(@RequestBody Venta venta){
        ventaService.save(venta);
    }

    @DeleteMapping("/productos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarProducto(@PathVariable Long id){
        productoService.delete(id);
    }



}
