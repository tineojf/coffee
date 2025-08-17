package com.cavoshcoffee.backend.service;

import com.cavoshcoffee.backend.dto.request.FavoritoRequestDTO;
import com.cavoshcoffee.backend.dto.response.FavoritoResponseDTO;
import com.cavoshcoffee.backend.entity.Favorito;
import com.cavoshcoffee.backend.entity.Producto;
import com.cavoshcoffee.backend.entity.Usuario;
import com.cavoshcoffee.backend.exceptions.EntityAlreadyExistsException;
import com.cavoshcoffee.backend.exceptions.ResourceNotFoundException;
import com.cavoshcoffee.backend.mapper.FavoritoMapper;
import com.cavoshcoffee.backend.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    public List<FavoritoResponseDTO> findAll() {
        return favoritoRepository.findAll()
                .stream()
                .map(FavoritoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<FavoritoResponseDTO> findByUsuarioId(Long usuarioId) {
        // todo check if usuarioId exists
        Optional<Usuario> usuario = usuarioService.findById(usuarioId);
        if (usuario.isEmpty()) {
            throw new ResourceNotFoundException("Usuario not found with id: " + usuarioId);
        }

        return favoritoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(FavoritoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public FavoritoResponseDTO save(FavoritoRequestDTO favorito) {
        // todo check if usuarioId exists & productoId exists & favorito exists
        Optional<Usuario> usuario = usuarioService.findById(favorito.getIdUsuario());
        if (usuario.isEmpty()) {
            throw new ResourceNotFoundException("Usuario not found with id: " + favorito.getIdUsuario());
        }

        Producto producto = productoService.findById(favorito.getIdProducto());

        Optional<Favorito> existingFavorito = favoritoRepository.findByUsuarioIdAndProductoId(favorito.getIdUsuario(), favorito.getIdProducto());
        if (existingFavorito.isPresent()) {
            throw new EntityAlreadyExistsException("Favorito already exists for usuarioId: "
                    + favorito.getIdUsuario()
                    + " and productoId: "
                    + favorito.getIdProducto());
        }

        Favorito newFavorito = FavoritoMapper.toEntity(usuario.get(), producto);
        favoritoRepository.save(newFavorito);

        return FavoritoMapper.toResponseDTO(newFavorito);
    }
}
