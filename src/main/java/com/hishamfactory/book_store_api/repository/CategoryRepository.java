package com.hishamfactory.book_store_api.repository;

import com.hishamfactory.book_store_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    //void delete(Optional<Category> category);

}
