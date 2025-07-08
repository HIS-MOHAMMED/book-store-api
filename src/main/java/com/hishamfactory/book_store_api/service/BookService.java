package com.hishamfactory.book_store_api.service;

import com.hishamfactory.book_store_api.entity.Book;
import com.hishamfactory.book_store_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    //    public void deleteBookByTitle(String book_title){
//        Optional<Book> book = bookRepository.findByTitle(book_title);
//        if(book.isPresent()){
//            bookRepository.delete(book);
//        }else{
//            throw new RuntimeException("book doesn't exists");
//        }
//    }
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
