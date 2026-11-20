package com.microservicio.controlusuario.controllers;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.controlusuario.models.Rol;
import com.microservicio.controlusuario.services.RolServiceImp;

@RestController
@RequestMapping ("/rol")
@CrossOrigin ("*") 
public class ControllerRol {
    

    @Autowired 
    private RolServiceImp rolServiceImp;


    @DeleteMapping
    public ResponseEntity<String> eliminarRol(@PathVariable Long id){

        rolServiceImp.eliminarRol(id);
        
        return ResponseEntity.ok().body("Id Eliminado correctamente!");
    }


    @PostMapping 
    public Rol crearRol(@RequestBody Rol rol){

        return rolServiceImp.crearRol(rol);
    }


    @GetMapping 
    public List <Rol> mostrarRoles(){

        return rolServiceImp.mostrarRol();
    }


    @PutMapping 
    public Rol editarRol(@PathVariable Long id, @RequestBody Rol rol){

        return rolServiceImp.editarRol(id, rol);
    }



    @GetMapping 
    public Rol mostrarRol(@PathVariable Long id){

        return rolServiceImp.obtenerRol(id);

    }

}
