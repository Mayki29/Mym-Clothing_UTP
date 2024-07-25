package com.utp.algoritmos.mymclothing.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntregaProducto {
    private Integer id;
    private Venta venta;
    private Date fechaSalida;
    private Date fechaEntrega;
}
