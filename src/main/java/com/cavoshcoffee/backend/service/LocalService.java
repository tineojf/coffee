package com.cavoshcoffee.backend.service;

import com.cavoshcoffee.backend.dto.request.LocalRequestDTO;
import com.cavoshcoffee.backend.dto.response.LocalResponseDTO;
import com.cavoshcoffee.backend.entity.Distrito;
import com.cavoshcoffee.backend.entity.Local;
import com.cavoshcoffee.backend.mapper.LocalMapper;
import com.cavoshcoffee.backend.repository.DistritoRepository;
import com.cavoshcoffee.backend.repository.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocalService {
    private final LocalRepository localRepository;
    private final DistritoRepository distritoRepository;

    public List<LocalResponseDTO> findAll() {
        return localRepository.findAll()
                .stream()
                .map(LocalMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<Local> findById(Long id) {
        return localRepository.findById(id);
    }

    public Local save(Local local) {
        return localRepository.save(local);
    }

    public void deleteById(Long id) {
        localRepository.deleteById(id);
    }
}
