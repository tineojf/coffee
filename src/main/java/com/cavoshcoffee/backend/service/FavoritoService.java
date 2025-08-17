package com.cavoshcoffee.backend.service;

import com.cavoshcoffee.backend.dto.request.FavoritoRequestDTO;
import com.cavoshcoffee.backend.dto.response.FavoritoResponseDTO;
import com.cavoshcoffee.backend.entity.Favorito;
import com.cavoshcoffee.backend.mapper.FavoritoMapper;
import com.cavoshcoffee.backend.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;

    public List<FavoritoResponseDTO> findAll() {
        return favoritoRepository.findAll()
                .stream()
                .map(FavoritoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<Favorito> findByUsuarioId(Long usuarioId) {
        return favoritoRepository.findAll()
                .stream()
                .filter(favorito -> favorito.getUsuario().getId().equals(usuarioId))
                .collect(Collectors.toList());
    }

    public void save(FavoritoRequestDTO favorito) {
        // buscar usuario id

        // buscar producto id

        // crear entidad Favorito

        // mapear DTO a entidad
    }
}
