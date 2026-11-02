package com.microservicio.controlusuario.Exceptions;

public class UsuarioAlreadyExistException extends RuntimeException {


    
    public UsuarioAlreadyExistException(String msj){

        super(msj);
    }

    
}
