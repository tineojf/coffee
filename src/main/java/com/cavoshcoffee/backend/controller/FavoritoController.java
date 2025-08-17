package com.cavoshcoffee.backend.controller;

import com.cavoshcoffee.backend.config.Constant;
import com.cavoshcoffee.backend.dto.GlobalResponse;
import com.cavoshcoffee.backend.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.API_VERSION + "/" + Constant.FAVORITE_TABLE)
@RequiredArgsConstructor
public class FavoritoController {
    private final FavoritoService favoritoService;

    @GetMapping
    public ResponseEntity<GlobalResponse> findAll() {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = favoritoService.findAll();
            status = HttpStatus.OK;
            message = "Favoritos retrieved successfully";
        } catch (Exception e) {
            data = null;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error retrieving favoritos";
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

    @GetMapping("/usuario/{id}")
    public ResponseEntity<GlobalResponse> getFavoritoById(@PathVariable Long id) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = favoritoService.findByUsuarioId(id);
            status = HttpStatus.OK;
            message = "Favoritos retrieved for user id: " + id;
        } catch (Exception e) {
            data = null;
            status = HttpStatus.NOT_FOUND;
            message = "Error retrieving favoritos for user id: " + id;
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
}
