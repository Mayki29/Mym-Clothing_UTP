package com.utp.algoritmos.mymclothing.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.algoritmos.mymclothing.models.Venta;
import com.utp.algoritmos.mymclothing.repositories.VentaRepository;

@Service
public class VentaServiceImp implements VentaService{

    @Autowired
    VentaRepository ventaRepository;

    @Override
    public List<Venta> findAll() {
        return (List<Venta>)ventaRepository.findAll();
    }

    @Override
    public void save(Venta venta) {
        Venta nVenta = venta;
        nVenta.setFecha(LocalDateTime.now());
        nVenta.setEstado("Pendiente");

        ventaRepository.save(nVenta);
        
    }
    

}
