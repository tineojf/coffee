package com.cavoshcoffee.backend.controller;

import com.cavoshcoffee.backend.config.Constant;
import com.cavoshcoffee.backend.dto.GlobalResponse;
import com.cavoshcoffee.backend.entity.Usuario;
import com.cavoshcoffee.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.API_VERSION + "/" + Constant.USER_TABLE)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<GlobalResponse> registrarUsuario(@RequestBody Usuario usuario) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Usuario nuevo = usuarioService.registrar(usuario);
            data = nuevo;
            status = HttpStatus.CREATED;
            message = "Usuario registrado exitosamente con ID: " + nuevo.getId();
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error al registrar usuario";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(status == HttpStatus.CREATED)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse> loginUsuario(@RequestBody Usuario usuario) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Optional<Usuario> usuarioEncontrado = usuarioService.login(usuario.getCorreo(), usuario.getPasswordd());
            if (usuarioEncontrado.isPresent()) {
                data = usuarioEncontrado.get();
                status = HttpStatus.OK;
                message = "Login exitoso";
            } else {
                status = HttpStatus.UNAUTHORIZED;
                data = null;
                message = "Credenciales incorrectas. No se pudo iniciar sesión.";
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error al intentar iniciar sesión";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<GlobalResponse> getAllUsuarios() {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = usuarioService.findAll();
            status = HttpStatus.OK;
            message = "Todos los usuarios obtenidos correctamente";
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error al obtener usuarios";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> getUsuarioById(@PathVariable Long id) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Optional<Usuario> usuarioBuscado = usuarioService.findById(id);
            if (usuarioBuscado.isPresent()) {
                data = usuarioBuscado.get();
                status = HttpStatus.OK;
                message = "Usuario encontrado con id: " + id;
            } else {
                status = HttpStatus.NOT_FOUND;
                data = null;
                message = "Usuario no encontrado con id: " + id;
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error al buscar usuario con id: " + id;
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<GlobalResponse> updateUsuario(@RequestBody Usuario usuario) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Optional<Usuario> usuarioBuscado = usuarioService.findById(usuario.getId());
            if (usuarioBuscado.isPresent()) {
                Usuario existente = usuarioBuscado.get();

                // Solo actualizas los campos editables
                existente.setNombres(usuario.getNombres());
                existente.setCorreo(usuario.getCorreo());

                if (usuario.getPasswordd() != null && !usuario.getPasswordd().isBlank()) {
                    existente.setPasswordd(usuarioService.encodePassword(usuario.getPasswordd()));
                }

                // OJO: no tocamos el login, porque ya existe y es NOT NULL
                Usuario actualizado = usuarioService.save(existente);

                data = actualizado;
                status = HttpStatus.OK;
                message = "Usuario actualizado exitosamente";
            } else {
                status = HttpStatus.BAD_REQUEST;
                data = null;
                message = "El usuario con ID " + usuario.getId() + " no existe. No se puede actualizar.";
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error al actualizar usuario";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(status == HttpStatus.OK)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<GlobalResponse> deleteUsuario(@PathVariable Long id) {
        HttpStatus status;
        String message;
        String details = null;

        try {
            Optional<Usuario> usuarioBuscado = usuarioService.findById(id);
            if (usuarioBuscado.isPresent()) {
                usuarioService.deleteById(id);
                status = HttpStatus.OK;
                message = "Usuario eliminado exitosamente con id: " + id;
            } else {
                status = HttpStatus.NOT_FOUND;
                message = "El usuario con ID " + id + " no existe. No se puede eliminar.";
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al eliminar usuario con id: " + id;
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(status == HttpStatus.OK)
                        .message(message)
                        .data(null)
                        .details(details)
                        .build()
        );
    }
}
