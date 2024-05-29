package com.utp.algoritmos.mymclothing.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {
    private int id;
    private Producto producto;
    private int cantidad;
    private Double subTotal;

    //void temporalmente
    public void calcularSubTotal(){}

}
