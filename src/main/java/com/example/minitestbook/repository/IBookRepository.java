package com.example.minitestbook.repository;

import com.example.minitestbook.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends PagingAndSortingRepository<Book, Long> {

    void save(Book book);

    Optional<Book> findById(Long id);

    void deleteById(Long id);


    Page<Book> findAllByNameContaining(Pageable pageable, String name);

    Page<Book> findByCategoryId(Long categoryId, Pageable pageable);

    boolean existsById(Long id);
}
