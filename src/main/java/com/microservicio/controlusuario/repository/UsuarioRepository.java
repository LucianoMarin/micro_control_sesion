package com.microservicio.controlusuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.controlusuario.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByUsuario(String usuario);

    boolean existsByUsuarioAndClave(String usuario, String clave);

    Optional<Usuario> findByUsuario(String usuario);
}
