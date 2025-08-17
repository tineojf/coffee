package com.cavoshcoffee.backend.service;

import com.cavoshcoffee.backend.entity.Distrito;
import com.cavoshcoffee.backend.exceptions.ResourceNotFoundException;
import com.cavoshcoffee.backend.repository.DistritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistritoService {
    private final DistritoRepository distritoRepository;

    public List<Distrito> findAll() {
        return distritoRepository.findAll();
    }

    public Distrito findById(Long id) {
        return distritoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Distrito not found - id: " + id));
    }
}
