package com.utp.algoritmos.mymclothing.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DETALLE_VENTA")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM")
    private Long id;
    @Column(name = "ID_VENTA")
    private Long idVenta;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO")
    private Producto producto;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Column(name = "SUB_TOTAL")
    private Double subTotal;

    //void temporalmente
    public void calcularSubTotal(){}

}
