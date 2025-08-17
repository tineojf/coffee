package com.cavoshcoffee.backend.controller;

import com.cavoshcoffee.backend.config.Constant;
import com.cavoshcoffee.backend.dto.GlobalResponse;
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
            message = Constant.str_allRetrieved(Constant.PRODUCT_TABLE);
            status = HttpStatus.OK;
        } catch (Exception e) {
            data = null;
            message = Constant.str_generalError(Constant.PRODUCT_TABLE);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .message(message)
                        .data(data)
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<GlobalResponse> findById(Long id) {
        HttpStatus status;
        Object data;
        String message;

        try {
            data = productoService.findById(id);
            message = Constant.str_retrieved(Constant.PRODUCT_TABLE, id);
            status = HttpStatus.OK;
        } catch (Exception e) {
            data = null;
            message = Constant.str_notFound(Constant.PRODUCT_TABLE, id);
            status = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .message(message)
                        .data(data)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> save(Producto producto) {
        HttpStatus status;
        Object data;
        String message;

        try {
            data = productoService.save(producto);
            message = Constant.str_saved(Constant.PRODUCT_TABLE);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            data = null;
            message = Constant.str_generalError(Constant.PRODUCT_TABLE);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .message(message)
                        .data(data)
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GlobalResponse> deleteById(@PathVariable Long id) {
        HttpStatus status;
        String message;

        try {
            productoService.deleteById(id);
            message = Constant.str_retrieved(Constant.PRODUCT_TABLE, id);
            status = HttpStatus.OK;
        } catch (Exception e) {
            message = e.getMessage();
            status = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .message(message)
                        .data(null)
                        .build()
        );
    }

}
