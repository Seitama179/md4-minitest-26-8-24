package com.example.minitestbook.service.category;

import com.example.minitestbook.model.Book;
import com.example.minitestbook.model.Category;
import com.example.minitestbook.model.dto.ICountBook;
import com.example.minitestbook.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService extends IGenerateService<Category> {
    Iterable<ICountBook> getNumberBook();

    Page<Book> findByCategory(Long id, Pageable pageable);
}
