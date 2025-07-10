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

    public Book updateBook(Long existing_book_id, Book updated_book){
        Book existingBook = bookRepository.findById(existing_book_id).get();
        if(updated_book.getTitle() != null){
            existingBook.setTitle(updated_book.getTitle());
        }
        else if(updated_book.getAuthor() != null){
            existingBook.setAuthor(updated_book.getAuthor());

        }else if(updated_book.getPrice() != 0){
            existingBook.setPrice(updated_book.getPrice());

        }else if( updated_book.getCategory() != null){
            existingBook.setCategory(updated_book.getCategory());
        }
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
