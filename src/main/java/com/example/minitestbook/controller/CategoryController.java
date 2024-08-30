package com.example.minitestbook.controller;

import com.example.minitestbook.model.Book;
import com.example.minitestbook.model.Category;
import com.example.minitestbook.model.dto.ICountBook;
import com.example.minitestbook.service.book.BookService;
import com.example.minitestbook.service.category.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    public CategoryController(CategoryService categoryService, BookService bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getCategories(Model model,@PageableDefault(value = 5) Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        model.addAttribute("categories", categories);
        Iterable<ICountBook> category1 = categoryService.getNumberBook();
        model.addAttribute("category1", category1);
        return "category/index";
    }

    @GetMapping("/create")
    public String createCategory(Model model){
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping("/create")
    public String saveCategory(@ModelAttribute("category") Category category,
                               RedirectAttributes redirectAttributes){
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Tạo mới thành công!");
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirect) {
        categoryService.remove(id);
        redirect.addFlashAttribute("message", "Xóa thành công!");
        return "redirect:/categories";
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

    @PostMapping("/update")
    public String editCategory(@ModelAttribute("category") Category category,
                               RedirectAttributes redirect){
        categoryService.save(category);
        redirect.addAttribute("message", "Sửa thành công");
        return "redirect:/categories";
    }

    @GetMapping("/detail/{id}")
    public String findBooksByCategory(@PathVariable Long id,@PageableDefault(value = 5) Pageable pageable,
                                      RedirectAttributes redirectAttributes){
        Page<Book> books = categoryService.findByCategory(id, pageable);
        redirectAttributes.addAttribute("books", books);
        return "redirect:/books";
    }

}
