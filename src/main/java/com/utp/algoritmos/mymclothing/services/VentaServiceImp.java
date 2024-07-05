package com.utp.algoritmos.mymclothing.services;

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
        return ventaRepository.findAll();
    }

}
