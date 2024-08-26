package com.example.minitestbook.model.dto;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class CategoryCountBookDTO {
    private Long id;
    private String categoryName;
    private Long bookCount;

    public CategoryCountBookDTO(){}

    public CategoryCountBookDTO(Long id, String categoryName, Long bookCount) {
        this.id = id;
        this.categoryName = categoryName;
        this.bookCount = bookCount;
    }

}
