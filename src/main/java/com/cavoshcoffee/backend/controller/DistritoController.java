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
        Object data;
        String message;
        HttpStatus status;

        try {
            data = distritoService.findAll();
            System.out.println(data);
            message = Constant.DISTRICT_ALL_RETRIEVED;
            status = HttpStatus.OK;
        } catch (Exception e) {
            data = null;
            message = Constant.DISTRICT_GENERAL_ERROR;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        GlobalResponse response = GlobalResponse.builder()
                .ok(data != null)
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> findById(@PathVariable Long id) {
        Object data;
        String message;
        HttpStatus status;

        try {
            data = distritoService.findById(id);
            message = Constant.DISTRICT_RETRIEVED + id;
            status = HttpStatus.OK;
        } catch (Exception e) {
            data = null;
            message = Constant.DISTRICT_NOT_FOUND + id;
            status = HttpStatus.NOT_FOUND;
        }

        GlobalResponse response = GlobalResponse.builder()
                .ok(data != null)
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
