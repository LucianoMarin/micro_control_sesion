package com.microservicio.controlusuario.Exceptions;

public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(String msj){

        super(msj);
    }
    
}
