package com.utp.algoritmos.mymclothing.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Integer id;
    
    @Column(name = "NOMBRE")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORIA")
    @JsonIgnore
    private List<Producto> producto;
    
    public Categoria(Integer id){
    this.id = id;   
    }
}
