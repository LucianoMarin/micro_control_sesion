package com.microservicio.controlusuario;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.microservicio.controlusuario.models.Rol;
import com.microservicio.controlusuario.models.Usuario;
import com.microservicio.controlusuario.repository.RolRepository;
import com.microservicio.controlusuario.repository.UsuarioRepository;

@SpringBootApplication
public class ControlusuarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlusuarioApplication.class, args);
    }

    // 2. AGREGAMOS PasswordEncoder COMO PARÁMETRO
    @Bean
    CommandLineRunner initData(UsuarioRepository repo, RolRepository repoRol, PasswordEncoder passwordEncoder) {
        return args -> {
            List<Rol> roles;

            if (repoRol.count() == 0) {
                roles = repoRol.saveAll(List.of(
                    new Rol("ROL_NORMAL"), // índice 0
                    new Rol("ROL_ADMIN")   // índice 1
                ));
            } else {
                roles = repoRol.findAll();
            }

            if (repo.count() == 0) {
                Rol rolNormal = roles.get(0);
                Rol rolAdmin = roles.get(1);

                // 3. ENCRIPTAMOS LA CLAVE ANTES DE GUARDARLA
                String claveEncriptada = passwordEncoder.encode("1234");

                List<Usuario> usuarios = List.of(
                    new Usuario("lmarin", claveEncriptada, 0, rolAdmin),
                    new Usuario("pgutierrez", claveEncriptada, 0, rolNormal),
                    new Usuario("psoto", claveEncriptada, 0, rolNormal)
                );

                repo.saveAll(usuarios);
            }
        };
    }
}