package com.utp.algoritmos.mymclothing.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTO")
public class Producto {
    @Id
    @Column(name = "ID_PRODUCTO")
    private Long id;
    @Column(name = "ID_CATEGORIA")
    private String categoria;
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "PRECIO_PRODUCCION")
    private Double precioProduccion;
    @Column(name = "PRECIO_VENTA")
    private Double precioVenta;
    @Column(name = "PROMEDIO_RESENA")
    private Double promedioResena;
    @Column(name = "STOCK_TOTAL")
    private int stock;
    @Column(name = "URL_IMAGEN")
    private String urlImagen;
    @Column(name = "TALLA")
    private String talla;
    @Column(name = "DESCUENTO")
    private int descuento;

    //void temporalmente
    public void calcularPromedioResena(){}
}
