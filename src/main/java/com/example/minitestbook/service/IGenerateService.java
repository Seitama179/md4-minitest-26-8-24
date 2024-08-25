package com.example.minitestbook.service;


import com.example.minitestbook.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGenerateService<T> {
    Page<T> findAll(Pageable pageable);

    Iterable<T> findAll();
//    void save(T t);
//
//    Optional<T> findById(Long id);
//
    void update(Long id, T t);

    void remove(Long id);
}
