package com.example.minitestbook.service;

import com.example.minitestbook.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IBookService extends IGenerateService<Book> {
    Page<Book> findAllByNameContaining(Pageable pageable, String name);
}
