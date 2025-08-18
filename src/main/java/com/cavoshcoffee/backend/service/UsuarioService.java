package com.cavoshcoffee.backend.service;

import com.cavoshcoffee.backend.dto.response.UsuarioResponseDTO;
import com.cavoshcoffee.backend.entity.Usuario;
import com.cavoshcoffee.backend.mapper.UsuarioMapper;
import com.cavoshcoffee.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;


    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo " + usuario.getCorreo() + " ya está en uso.");
        }
        usuario.setPasswordd(passwordEncoder.encode(usuario.getPasswordd()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(String correo, String passwordPlano) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(passwordPlano, usuario.getPasswordd())) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }
}
