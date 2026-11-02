package com.microservicio.controlusuario;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.microservicio.controlusuario.models.Usuario;
import com.microservicio.controlusuario.repository.UsuarioRepository;

@SpringBootApplication
public class ControlusuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlusuarioApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UsuarioRepository repo){
			return args -> {
			

				/* 	LLENADO DE DATOS, (PIDE 3 INGRESOS EN LA BD, ESTOS SE INSERTARAN SI NO EXISTE NADA EN LA BD) */

				if(repo.count()==0){

					List <Usuario> usuarios=List.of(
					new Usuario("lmarin","1234",0,0),
					new Usuario("pgutierrez","1234",0,0),
					new Usuario("psoto","1234",0,0)
				);

				repo.saveAll(usuarios);
			
			}

		};

	}

}
