package com.microservicio.controlusuario.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Entity 
@Getter 
@Setter 

@Table (name="usuario")


public class Usuario{



    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (unique=true, name="usuario")
    @NotBlank(message = "El campo usuario no puede estar vacio")
    private String usuario;

    @Column (name="clave")
    @NotBlank(message = "El campo clave no puede estar vacio en medico")
    private String clave;

    @Column (name="intento")
    private Integer intento;

    @Column (name="rol")
    private Integer rol;

    

    public Usuario(){}

    
    public Usuario(String usuario,String clave, Integer intento, Integer rol){

        this.usuario=usuario;
        this.clave=clave;
        this.intento=intento;
        this.rol=rol;


    }


    public Usuario(Long id,String usuario,String clave, Integer intento, Integer rol){

        this.id=id;
        this.usuario=usuario;
        this.clave=clave;
        this.intento=intento;
        this.rol=rol;


    }


}