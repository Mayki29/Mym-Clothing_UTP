package com.utp.algoritmos.mymclothing.services;

import java.util.List;

import com.utp.algoritmos.mymclothing.models.Venta;

public interface VentaService {
    List<Venta> findAll();
    void save(Venta venta);
}
