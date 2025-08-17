package com.cavoshcoffee.backend.service;

import java.util.List;

public interface GenericService<T, S> {
    List<T> findAll();

    T findById(Long id);

    T save(S entity);

    void deleteById(Long id);
}
