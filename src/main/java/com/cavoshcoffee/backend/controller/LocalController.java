package com.cavoshcoffee.backend.controller;

import com.cavoshcoffee.backend.config.Constant;
import com.cavoshcoffee.backend.dto.GlobalResponse;
import com.cavoshcoffee.backend.dto.response.LocalResponseDTO;
import com.cavoshcoffee.backend.entity.Local;
import com.cavoshcoffee.backend.exceptions.BadRequestException;
import com.cavoshcoffee.backend.exceptions.ResourceNotFoundException;
import com.cavoshcoffee.backend.service.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.API_VERSION + "/" + Constant.LOCAL_TABLE)
@RequiredArgsConstructor
public class LocalController {

    private final LocalService localService;

    @PostMapping("/crear")
    public ResponseEntity<GlobalResponse> registrarLocal(@RequestBody Local local) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Local nuevo = localService.save(local);
            data = nuevo;
            status = HttpStatus.CREATED;
            message = "Local registrado exitosamente con ID: " + nuevo.getId();
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error al registrar local";
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

    @GetMapping
    public ResponseEntity<GlobalResponse> getAllLocales() {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {

            data = localService.findAll();
            status = HttpStatus.OK;
            message = "Todos los locales obtenidos correctamente";
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error al obtener locales";
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

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<GlobalResponse> getLocalById(@PathVariable Long id) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Optional<Local> localBuscado = localService.findById(id);
            if (localBuscado.isPresent()) {
                data = localBuscado.get();
                status = HttpStatus.OK;
                message = "Local encontrado con id: " + id;
            } else {
                status = HttpStatus.NOT_FOUND;
                data = null;
                message = "Local no encontrado con id: " + id;
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error al buscar local con id: " + id;
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
    public ResponseEntity<GlobalResponse> updateLocal(@RequestBody Local local) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Optional<Local> localBuscado = localService.findById(local.getId());
            if (localBuscado.isPresent()) {
                Local existente = localBuscado.get();

                existente.setRazonSocial(local.getRazonSocial());
                existente.setDireccion(local.getDireccion());
                existente.setDistrito(local.getDistrito());
                existente.setHorario(local.getHorario());
                existente.setLatitud(local.getLatitud());
                existente.setLongitud(local.getLongitud());

                Local actualizado = localService.save(existente);

                data = actualizado;
                status = HttpStatus.OK;
                message = "Local actualizado exitosamente";
            } else {
                status = HttpStatus.BAD_REQUEST;
                data = null;
                message = "El local con ID " + local.getId() + " no existe. No se puede actualizar.";
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error al actualizar local";
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
    public ResponseEntity<GlobalResponse> deleteLocal(@PathVariable Long id) {
        HttpStatus status;
        String message;
        String details = null;

        try {
            Optional<Local> localBuscado = localService.findById(id);
            if (localBuscado.isPresent()) {
                localService.deleteById(id);
                status = HttpStatus.OK;
                message = "Local eliminado exitosamente con id: " + id;
            } else {
                status = HttpStatus.NOT_FOUND;
                message = "El local con ID " + id + " no existe. No se puede eliminar.";
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al eliminar local con id: " + id;
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
