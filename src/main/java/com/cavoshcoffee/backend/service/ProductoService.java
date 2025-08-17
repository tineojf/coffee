package com.cavoshcoffee.backend.service;

import com.cavoshcoffee.backend.entity.Producto;
import com.cavoshcoffee.backend.exceptions.ResourceNotFoundException;
import com.cavoshcoffee.backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements GenericService<Producto> {
    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found - id: " + id));
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto not found - id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
