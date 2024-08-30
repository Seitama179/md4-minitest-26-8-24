package com.example.minitestbook.service.category;

import com.example.minitestbook.model.Book;
import com.example.minitestbook.model.Category;
import com.example.minitestbook.model.dto.ICountBook;
import com.example.minitestbook.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void update(Long id, Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteCategory(id);
    }

    @Override
    public Iterable<ICountBook> getNumberBook() {
        return categoryRepository.getNumberBook();
    }

    @Override
    public Page<Book> findByCategory(Long id, Pageable pageable) {
        return categoryRepository.findByCategory(id, pageable);
    }
}
