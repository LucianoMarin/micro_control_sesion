package com.microservicio.controlusuario;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.microservicio.controlusuario.models.Rol;
import com.microservicio.controlusuario.models.Usuario;
import com.microservicio.controlusuario.repository.RolRepository;
import com.microservicio.controlusuario.repository.UsuarioRepository;

@SpringBootApplication
public class ControlusuarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlusuarioApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UsuarioRepository repo, RolRepository repoRol) {
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

                List<Usuario> usuarios = List.of(
                    new Usuario("lmarin", "1234", 0, rolAdmin),
                    new Usuario("pgutierrez", "1234", 0, rolNormal),
                    new Usuario("psoto", "1234", 0, rolNormal)
                );

                repo.saveAll(usuarios);
            }
        };
    }
}
