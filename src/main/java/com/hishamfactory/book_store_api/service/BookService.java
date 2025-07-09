package com.hishamfactory.book_store_api.service;

import com.hishamfactory.book_store_api.entity.Book;
import com.hishamfactory.book_store_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book){
        if(bookRepository.findByTitle(book.getTitle()).isPresent()){
            throw new RuntimeException("this book already exists");
        }
        bookRepository.save(book);
    }
    public Book getBookByTitle(String book_title){
        return bookRepository.findByTitle(book_title).orElseThrow(() -> new RuntimeException("book not found"));
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
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
