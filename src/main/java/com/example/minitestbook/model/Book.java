package com.example.minitestbook.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    private double price;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    private String image;

    public Book() {}

    public Book(Long id, String name, String author, double price, Category category, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public Book(String name, String author, double price, Category category) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
    }
}
