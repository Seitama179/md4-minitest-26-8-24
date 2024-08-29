package com.example.minitestbook.repository;

import com.example.minitestbook.model.Book;
import com.example.minitestbook.model.Category;
import com.example.minitestbook.model.dto.ICountBook;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "call DeleteCategory(:id)")
    void deleteCategory(@Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT category.name, count(book.id) as number FROM category LEFT JOIN book on category.id = category_id GROUP BY category.name ;")
    Iterable<ICountBook> getNumberBook();

    @Query(nativeQuery = true, value = "Select * from book where category_id = :id ")
    Page<Book> findByCategory(@Param("id") Long id, Pageable pageable);
}
