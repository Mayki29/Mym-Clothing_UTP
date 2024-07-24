package com.utp.algoritmos.mymclothing.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {
    private Long id;
    private Integer categoria;
    private String nombreProducto;
    private String descripcion;
    private Double precioProduccion;
    private Double precioVenta;
    private Integer stock;
    private MultipartFile imagen;
    private String talla;
    private Integer descuento;
}
