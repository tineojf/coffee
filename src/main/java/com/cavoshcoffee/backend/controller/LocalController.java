package com.cavoshcoffee.backend.controller;

import com.cavoshcoffee.backend.entity.Local;
import com.cavoshcoffee.backend.exceptions.BadRequestException;
import com.cavoshcoffee.backend.exceptions.ResourceNotFoundException;
import com.cavoshcoffee.backend.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/local")
public class LocalController {
    @Autowired
    private LocalService localService;

    @PostMapping
    public ResponseEntity<String> createLocal(@RequestBody Local local) throws BadRequestException {
        Optional<Local> localBuscado = localService.findById(local.getId());
        if (localBuscado.isPresent()) {
            throw new BadRequestException("El Local con ID " + local.getId() + " ya existe. No se puede crear.");
        } else {
            localService.save(local);
            return ResponseEntity.ok("Local creado exitosamente");
        }
    }

    @GetMapping
    public ResponseEntity<List<Local>> getAllLocales() {
        return ResponseEntity.ok(localService.findAll());
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Local> getLocalById(Long id) {
        Optional<Local> localBuscado = localService.findById(id);
        return localBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> updateLocal(@RequestBody Local local) {
        Optional<Local> localBuscado = localService.findById(local.getId());
        if (localBuscado.isPresent()) {
            localService.save(local);
            return ResponseEntity.ok("Local actualizado exitosamente");
        } else {
            return ResponseEntity.badRequest().body("El Local con ID " + local.getId() + " no existe. No se puede actualizar.");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteLocal(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Local> localBuscado = localService.findById(id);
        if (localBuscado.isPresent()) {
            localService.deleteById(id);
            return ResponseEntity.ok("Local eliminado exitosamente");
        } else {
            throw new ResourceNotFoundException("El Local con ID " + id + " no existe. No se puede eliminar.");
        }
    }
}
