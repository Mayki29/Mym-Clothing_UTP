package com.utp.algoritmos.mymclothing.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "")
public class Venta {
    private int id;
    private Usuario usuario;
    private List<DetalleVenta> detallesVenta;
    private Date fecha;
    private Double total;

    //void temporalmente
    public void generarVenta(){}
    public void calcularTotal(){}
}
