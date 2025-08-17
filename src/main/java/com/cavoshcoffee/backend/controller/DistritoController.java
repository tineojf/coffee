package com.cavoshcoffee.backend.controller;

import com.cavoshcoffee.backend.config.Constant;
import com.cavoshcoffee.backend.dto.GlobalResponse;
import com.cavoshcoffee.backend.service.DistritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.API_VERSION + "/" + Constant.DISTRICT_TABLE)
@RequiredArgsConstructor
public class DistritoController {
    private final DistritoService distritoService;

    @GetMapping
    public ResponseEntity<GlobalResponse> findAll() {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = distritoService.findAll();
            message = "All " + Constant.DISTRICT_TABLE + " retrieved";
            status = HttpStatus.OK;
        } catch (Exception e) {
            data = null;
            message = "Error retrieving " + Constant.DISTRICT_TABLE;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
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
    public ResponseEntity<GlobalResponse> findById(@PathVariable Long id) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = distritoService.findById(id);
            message = Constant.DISTRICT_TABLE + " retrieved - id: " + id;
            status = HttpStatus.OK;
        } catch (Exception e) {
            data = null;
            message = "Error retrieving " + Constant.DISTRICT_TABLE + " with id: " + id;
            status = HttpStatus.NOT_FOUND;
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
