package com.microservicio.controlusuario.services;

import com.microservicio.controlusuario.models.Rol;
import com.microservicio.controlusuario.models.Usuario;
import com.microservicio.controlusuario.repository.RolRepository;
import com.microservicio.controlusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuario.setIntento(0);

        Rol rolNormal = rolRepository.findAll().stream()
                .filter(r -> r.getNombre().equals("ROL_NORMAL"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: El rol ROL_NORMAL no existe en la BD."));

        usuario.setRol(rolNormal);

        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario editarUsuario(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setUsuario(usuario.getUsuario());
            if (usuario.getClave() != null && !usuario.getClave().isEmpty()) {
                existente.setClave(passwordEncoder.encode(usuario.getClave()));
            }
            existente.setIntento(usuario.getIntento());
            existente.setRol(usuario.getRol());
            return usuarioRepository.save(existente);
        }
        return null;
    }

    @Override
    public List<Usuario> mostrarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public String loguearUsuario(Usuario usuario) {
        return "";
    }
}