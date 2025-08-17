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
            message = Constant.str_allRetrieved(Constant.DISTRICT_TABLE);
            status = HttpStatus.OK;
        } catch (Exception e) {
            data = null;
            message = Constant.str_generalError(Constant.DISTRICT_TABLE);
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
        Object data;
        String message;
        HttpStatus status;

        try {
            data = distritoService.findById(id);
            message = Constant.str_retrieved(Constant.DISTRICT_TABLE, id);
            status = HttpStatus.OK;
        } catch (Exception e) {
            data = null;
            message = Constant.str_notFound(Constant.DISTRICT_TABLE, id);
            status = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .build()
        );
    }
}
