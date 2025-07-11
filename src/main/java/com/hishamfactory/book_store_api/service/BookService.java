package com.hishamfactory.book_store_api.service;

import com.hishamfactory.book_store_api.entity.Book;
import com.hishamfactory.book_store_api.entity.Category;
import com.hishamfactory.book_store_api.repository.BookRepository;
import com.hishamfactory.book_store_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Book addBook(Book book){
        if(bookRepository.findByTitle(book.getTitle()).isPresent()){
            throw new RuntimeException("this book already exists");
        }
        return bookRepository.save(book);
    }
    public Book getBookByTitle(String book_title){
        return bookRepository.findByTitle(book_title).orElseThrow(() -> new RuntimeException("book not found"));
    }
    public Book getBookByID(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("book not found"));
    }

    public Book updateBook(Long book_id , Book updated_book){
        Book existingBook = bookRepository.findById(book_id).orElseThrow(()-> new RuntimeException("book not found."));
            existingBook.setTitle(updated_book.getTitle());
            existingBook.setAuthor(updated_book.getAuthor());
            existingBook.setPrice(updated_book.getPrice());
            Category category = categoryRepository.findById(updated_book.getCategory().getId()).orElseThrow(()-> new RuntimeException("category not found"));
            existingBook.setCategory(category);
            return bookRepository.save(existingBook);
    }
    public void deleteBookByTitle(String book_title){
        Optional<Book> optionalBook = bookRepository.findByTitle(book_title);
        if(optionalBook.isEmpty()){
            throw new RuntimeException("book doesn't exists");
        }
        else{
            bookRepository.delete(optionalBook.get());
        }
    }
    public void deleteBookByID(Long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            throw new RuntimeException("book doesn't exists");
        }
        else{
            bookRepository.delete(optionalBook.get());
        }
    }
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
