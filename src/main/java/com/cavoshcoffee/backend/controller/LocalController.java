package com.cavoshcoffee.backend.controller;

import com.cavoshcoffee.backend.config.Constant;
import com.cavoshcoffee.backend.entity.Local;
import com.cavoshcoffee.backend.exceptions.BadRequestException;
import com.cavoshcoffee.backend.exceptions.ResourceNotFoundException;
import com.cavoshcoffee.backend.service.LocalService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> createLocal(@RequestBody Local local) throws BadRequestException {
        if (local.getId() != null && localService.findById(local.getId()).isPresent()) {
            throw new BadRequestException("El Local con ID " + local.getId() + " ya existe. No se puede crear.");
        }
        Local nuevo = localService.save(local);
        return ResponseEntity.ok("Local creado exitosamente con ID: " + nuevo.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Local>> getAllLocales() {
        return ResponseEntity.ok(localService.findAll());
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Local> getLocalById(@PathVariable Long id) throws ResourceNotFoundException {
        return localService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("El Local con ID " + id + " no existe."));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> updateLocal(@PathVariable Long id, @RequestBody Local local) throws ResourceNotFoundException {
        Optional<Local> localBuscado = localService.findById(id);
        if (localBuscado.isEmpty()) {
            throw new ResourceNotFoundException("El Local con ID " + id + " no existe. No se puede actualizar.");
        }
        local.setId(id);
        localService.save(local);
        return ResponseEntity.ok("Local actualizado exitosamente con ID: " + id);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteLocal(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Local> localBuscado = localService.findById(id);
        if (localBuscado.isPresent()) {
            localService.deleteById(id);
            return ResponseEntity.ok("Local eliminado exitosamente con ID: " + id);
        } else {
            throw new ResourceNotFoundException("El Local con ID " + id + " no existe. No se puede eliminar.");
        }
    }
}
