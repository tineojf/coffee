package com.cavoshcoffee.backend.controller;

import com.cavoshcoffee.backend.entity.Usuario;
import com.cavoshcoffee.backend.exceptions.BadRequestException;
import com.cavoshcoffee.backend.exceptions.ResourceNotFoundException;
import com.cavoshcoffee.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    @Autowired
    private  UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> createUsuario(@RequestBody Usuario usuario) throws BadRequestException {
        Optional<Usuario> usuarioBuscado = usuarioService.findById(usuario.getId());
        if (usuarioBuscado.isPresent()) {
            throw new BadRequestException("El usuario con ID " + usuario.getId() + " ya existe. No se puede crear.");
        } else {
            usuarioService.save(usuario);
            return ResponseEntity.ok("Usuario creado exitosamente");
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Usuario> getUsuarioById(Long id) {
        Optional<Usuario> usuarioBuscado = usuarioService.findById(id);
        return usuarioBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

   @PutMapping
    public ResponseEntity<String> createOrUpdateUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioBuscado = usuarioService.findById(usuario.getId());
        if (usuarioBuscado.isPresent()) {
            usuarioService.save(usuario);
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } else {
            return ResponseEntity.badRequest().body("El usuario con ID " + usuario.getId() + " no existe. No se puede actualizar.");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioService.findById(id);
        if (usuarioBuscado.isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } else {
            throw new  ResourceNotFoundException("El usuario con ID " + id + " no existe. No se puede eliminar.");
        }
    }
}
