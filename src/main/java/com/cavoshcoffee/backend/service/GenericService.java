package com.cavoshcoffee.backend.service;

import java.util.List;

public interface GenericService<T> {
    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    void deleteById(Long id);
}
