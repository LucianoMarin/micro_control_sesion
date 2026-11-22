package com.microservicio.controlusuario.controllers;

import com.microservicio.controlusuario.models.Usuario;
import com.microservicio.controlusuario.repository.UsuarioRepository;
import com.microservicio.controlusuario.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final int MAX_INTENTOS = 3;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        Optional<Usuario> opt = usuarioRepository.findByUsuario(username);
        if (opt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
        }

        Usuario usuario = opt.get();

        if (usuario.getIntento() != null && usuario.getIntento() >= MAX_INTENTOS) {
            return ResponseEntity.status(423).body(Map.of("error", "Usuario bloqueado"));
        }

        if (!passwordEncoder.matches(password, usuario.getClave())) {
            usuario.setIntento((usuario.getIntento() == null ? 0 : usuario.getIntento()) + 1);
            usuarioRepository.save(usuario);
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
        }

        usuario.setIntento(0);
        usuarioRepository.save(usuario);

        String token = jwtService.generarToken(usuario.getUsuario());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "rol", usuario.getRol() != null ? usuario.getRol().getNombre() : ""
        ));
    }
}