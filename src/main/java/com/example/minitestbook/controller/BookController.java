package com.example.minitestbook.controller;

import com.example.minitestbook.model.Book;
import com.example.minitestbook.model.BookForm;
import com.example.minitestbook.model.Category;
import com.example.minitestbook.service.book.IBookService;
import com.example.minitestbook.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;
    private final ICategoryService categoryService;
    public BookController(IBookService bookService, ICategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public Iterable<Category> listCategories() {
        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView index(@PageableDefault(value = 5) Pageable pageable) {
        Page<Book> books = bookService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("book/index");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("book/create");
        modelAndView.addObject("books", new Book());
        return modelAndView;
    }

    @Value("${file-upload}")
    private String upload;

    @PostMapping("/create")
    public String save(@ModelAttribute("book") BookForm bookForm,
                       RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("book/index");
        MultipartFile file = bookForm.getImage();
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            Book book = new Book();
            book.setId(bookForm.getId());
            book.setName(bookForm.getName());
            book.setAuthor(bookForm.getAuthor());
            book.setPrice(bookForm.getPrice());
            book.setCategory(bookForm.getCategory());
            book.setImage(fileName);
            bookService.save(book);
            redirectAttributes.addFlashAttribute("message", "Tạo mới thành công!");
        }
        return "redirect:/books";
    }

//    @PostMapping("/create")
//    public ModelAndView save(BookForm bookForm) {
//        ModelAndView modelAndView = new ModelAndView("book/index");
//        MultipartFile file = bookForm.getImage();
//        String fileName = file.getOriginalFilename();
//        try {
//            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        finally {
//            Book book = new Book();
//            book.setId(bookForm.getId());
//            book.setName(bookForm.getName());
//            book.setAuthor(bookForm.getAuthor());
//            book.setPrice(bookForm.getPrice());
//            book.setCategory(bookForm.getCategory());
//            book.setImage(fileName);
//            bookService.save(book);
//            modelAndView.addObject("message", "Tạo mới thành công!");
//        }
//        return modelAndView;
//    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("book/detail");
            modelAndView.addObject("book", book.get());
            return modelAndView;
        } else {
            return new ModelAndView("error_404");
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
    ModelAndView modelAndView = new ModelAndView("book/edit");
    modelAndView.addObject("book", bookService.findById(id));
    return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(BookForm bookForm) {
        ModelAndView modelAndView = new ModelAndView("redirect:/books");
        MultipartFile file = bookForm.getImage();
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload+fileName));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setAuthor(bookForm.getAuthor());
        book.setPrice(bookForm.getPrice());
        book.setCategory(bookForm.getCategory());
        book.setImage(fileName);
        book.setId(bookForm.getId());
        bookService.update(book.getId(), book);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        bookService.remove(id);
        redirect.addFlashAttribute("message", "Xóa thành công!");
        return "redirect:/books";
    }

    @PostMapping("/search")
    public ModelAndView findBookByName(@RequestParam("search") Optional<String> search,
                                   @PageableDefault(value = 5) Pageable pageable){
        Page<Book> books;
        if(search.isPresent()){
            books = bookService.findAllByNameContaining(pageable, search.get());
        } else {
            books = bookService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("book/index");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/category")
    public ModelAndView searchByCategory(@RequestParam("categoryId") Long categoryId, @PageableDefault(value = 5) Pageable pageable) {
        Page<Book> books = bookService.findByCategoryId(categoryId, pageable);
        ModelAndView modelAndView = new ModelAndView("book/index");
        modelAndView.addObject("books", books);
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
}
