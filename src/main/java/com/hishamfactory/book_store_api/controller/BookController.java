package com.hishamfactory.book_store_api.controller;


import com.hishamfactory.book_store_api.entity.Book;
import com.hishamfactory.book_store_api.entity.Category;
import com.hishamfactory.book_store_api.service.BookService;
import com.hishamfactory.book_store_api.service.CategoryService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book){
            Category category = categoryService.getCategoryByID(book.getCategory().getId());
            book.setCategory(category);
            Book addedBook = bookService.addBook(book);
            return ResponseEntity.ok(addedBook);
    }

    @GetMapping("/getBookByID/{book_id}")
    public ResponseEntity<Book> getBookByID(@PathVariable Long book_id){
        Book book = bookService.getBookByID(book_id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/updateBook/{book_id}")
    public ResponseEntity<Book> updateBookInfo(@PathVariable Long book_id, @RequestBody Book updated_book){
        Book updatedBook = bookService.updateBook(book_id, updated_book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/deleteBook/{book_id}")
    public String deleteBookByID(@PathVariable Long book_id){
        Book book = bookService.getBookByID(book_id);
        bookService.deleteBookByTitle(book.getTitle());
        return "the " + book.getTitle() + " was deleted.";
    }
    @GetMapping("/search")
    public ResponseEntity<Book> getBookByTitle(@RequestParam String book_title){
        Book book = bookService.getBookByTitle(book_title);
        return ResponseEntity.ok(book);
    }
}
