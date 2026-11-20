package com.microservicio.controlusuario.services;

import java.util.List;

import com.microservicio.controlusuario.models.Rol;

public interface RolService {
    

    /* PLANTILLA DEL CRUD DE ROL */

    Rol crearRol(Rol rol);

    void eliminarRol(Long id);

    Rol editarRol(Long id, Rol rol);

    List <Rol> mostrarRol();

    Rol obtenerRol(Long id);




}
