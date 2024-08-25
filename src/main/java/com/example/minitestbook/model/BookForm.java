package com.example.minitestbook.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class BookForm {
    private Long id;

    private String name;
    private String author;
    private double price;
    private Category category;
    private MultipartFile image;

    public BookForm() {}

    public BookForm(Long id, String name, String author, double price, Category category, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.image = image;
    }

}
