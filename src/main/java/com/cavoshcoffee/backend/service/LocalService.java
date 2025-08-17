package com.cavoshcoffee.backend.service;

import com.cavoshcoffee.backend.entity.Local;
import com.cavoshcoffee.backend.repository.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalService {
    private final LocalRepository localRepository;

    public List<Local> findAll() {
        return localRepository.findAll();
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
