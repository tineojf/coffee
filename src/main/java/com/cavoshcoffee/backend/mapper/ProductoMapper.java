package com.cavoshcoffee.backend.mapper;

import com.cavoshcoffee.backend.dto.request.ProductoRequestDTO;
import com.cavoshcoffee.backend.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public static Producto toEntity(ProductoRequestDTO producto) {
        if (producto == null) {
            return null;
        }

        Producto newProducto = new Producto();
        newProducto.setNombre(producto.getNombre());
        newProducto.setDescripcion(producto.getDescripcion());
        newProducto.setPrecio(producto.getPrecio());
        newProducto.setDetalle(producto.getDetalle());
        newProducto.setCategoria(producto.getCategoria());
        newProducto.setImagen(producto.getImagen());
        newProducto.setEsNuevo(true);
        return newProducto;
    }
}
