package com.example.minitestbook.service;

import com.example.minitestbook.model.Book;
import com.example.minitestbook.model.Category;
import com.example.minitestbook.model.dto.CategoryCountBookDTO;
import com.example.minitestbook.model.dto.ICountBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService extends IGenerateService<Category> {
    Iterable<ICountBook> getNumberBook();
}
