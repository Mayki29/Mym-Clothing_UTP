package com.utp.algoritmos.mymclothing.models;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "DNI")
    private String dni;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EDAD")
    private String edad;
    @Column(name = "ID_ROL")
    private Long rol;
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
    

    //void temporalmente
    public void iniciarSesion(){}
    public void registrar(){}
}
