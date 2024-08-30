package com.example.minitestbook.service.book;

import com.example.minitestbook.model.Book;
import com.example.minitestbook.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IBookService extends IGenerateService<Book> {
    Page<Book> findAllByNameContaining(Pageable pageable, String name);

    Page<Book> findByCategoryId(Long categoryId, Pageable pageable);
}
