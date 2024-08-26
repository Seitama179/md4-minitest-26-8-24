package com.example.minitestbook.controller;

import com.example.minitestbook.model.Category;
import com.example.minitestbook.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/books/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategories(Model model,@PageableDefault(value = 5) Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        model.addAttribute("categories", categories);
        return "category/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        categoryService.remove(id);
        model.addAttribute("categories", categoryService.findAll());
        return "category/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "category/edit";
        } else {
            return "error_404";
        }
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@ModelAttribute("category") Category category,
                               RedirectAttributes redirect){
        categoryService.save(category);
        redirect.addAttribute("message", "Sửa thành công");
        return "redirect:/books/categories";
    }


}
