package com.cavoshcoffee.backend.service;

import com.cavoshcoffee.backend.dto.request.ProductoRequestDTO;
import com.cavoshcoffee.backend.entity.Producto;
import com.cavoshcoffee.backend.exceptions.ResourceNotFoundException;
import com.cavoshcoffee.backend.mapper.ProductoMapper;
import com.cavoshcoffee.backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements GenericService<Producto, ProductoRequestDTO> {
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
    public Producto save(ProductoRequestDTO producto) {
        Producto newProducto = ProductoMapper.toEntity(producto);
        return productoRepository.save(newProducto);
    }

    @Override
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto not found - id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
