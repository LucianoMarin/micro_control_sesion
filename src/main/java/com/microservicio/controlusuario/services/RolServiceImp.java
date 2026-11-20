package com.microservicio.controlusuario.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.controlusuario.models.Rol;
import com.microservicio.controlusuario.repository.RolRepository;

@Service
public class RolServiceImp implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol crearRol(Rol rol) {

        return rolRepository.save(rol);
    }

    @Override
    public Rol editarRol(Long id, Rol rol) {
    Rol encontrado = rolRepository.findById(id).orElseThrow();

    if(rol.getNombre()!=null && !rol.getNombre().isBlank()){

        encontrado.setNombre(rol.getNombre());

    }

        return rolRepository.save(encontrado);
    }



    @Override
    public void eliminarRol(Long id) {

        rolRepository.deleteById(id);

    }


    @Override
    public List<Rol> mostrarRol() {

        return rolRepository.findAll();
    }

    @Override
    public Rol obtenerRol(Long id) {
        
    return rolRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("No se encontró el rol con ID: " + id));

    }
}
