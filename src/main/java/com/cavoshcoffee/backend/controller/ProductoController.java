package com.cavoshcoffee.backend.controller;

import com.cavoshcoffee.backend.config.Constant;
import com.cavoshcoffee.backend.dto.GlobalResponse;
import com.cavoshcoffee.backend.dto.request.ProductoRequestDTO;
import com.cavoshcoffee.backend.entity.Producto;
import com.cavoshcoffee.backend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.API_VERSION + "/" + Constant.PRODUCT_TABLE)
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<GlobalResponse> findAll() {
        HttpStatus status;
        Object data;
        String message;

        try {
            data = productoService.findAll();
            message = "All " + Constant.PRODUCT_TABLE + " retrieved";
            status = HttpStatus.OK;
        } catch (Exception e) {
            data = null;
            message = "Error retrieving " + Constant.PRODUCT_TABLE;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> findById(@PathVariable Long id) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            status = HttpStatus.OK;
            data = productoService.findById(id);
            message = Constant.PRODUCT_TABLE + " retrieved - id: " + id;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving " + Constant.PRODUCT_TABLE + " with id: " + id;
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

    @PostMapping
    public ResponseEntity<GlobalResponse> save(@RequestBody ProductoRequestDTO producto) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            status = HttpStatus.CREATED;
            data = productoService.save(producto);
            message = Constant.PRODUCT_TABLE + " created successfully";
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error creating " + Constant.PRODUCT_TABLE + ": " + e.getMessage();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteById(@PathVariable Long id) {
        HttpStatus status;
        String message;
        String details = null;

        try {
            status = HttpStatus.OK;
            productoService.deleteById(id);
            message = Constant.PRODUCT_TABLE + " deleted successfully - id: " + id;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            message = "Error deleting " + Constant.PRODUCT_TABLE + " with id: " + id;
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
