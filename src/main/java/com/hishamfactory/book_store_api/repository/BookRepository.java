package com.hishamfactory.book_store_api.repository;

import com.hishamfactory.book_store_api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
  //  void delete(Optional<Book> book);
}
