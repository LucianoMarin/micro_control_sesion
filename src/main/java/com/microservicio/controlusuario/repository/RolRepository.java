package com.microservicio.controlusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.controlusuario.models.Rol;

public interface RolRepository extends JpaRepository <Rol, Long> {}
