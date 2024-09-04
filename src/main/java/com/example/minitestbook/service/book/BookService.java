package com.example.minitestbook.service.book;

import com.example.minitestbook.model.Book;
import com.example.minitestbook.repository.IBookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {
    private final IBookRepository bookRepository;
    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Iterable<Book> findAll() {
        return null;
    }

        @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> findAllByNameContaining(Pageable pageable, String name) {
        return bookRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Page<Book> findByCategoryId(Long categoryId, Pageable pageable) {
        return bookRepository.findByCategoryId(categoryId, pageable);
    }

    @Override
    public boolean existsById(Long Id) {
        return bookRepository.existsById(Id);
    }

    @Override
    public void update(Long id, Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }
}
