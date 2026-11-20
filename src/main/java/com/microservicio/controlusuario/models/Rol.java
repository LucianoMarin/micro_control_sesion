package com.microservicio.controlusuario.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter 
@Setter 
public class Rol {
 
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="nombre" , unique=true)
    @NotBlank(message = "El campo clave no puede estar vacio en medico")
    private String nombre;


    public Rol (){}



    public Rol(String nombre){

        this.nombre=nombre;
        
    }

    public Rol(Long id, String nombre){

        this.id=id;
        this.nombre=nombre;

    }






}
