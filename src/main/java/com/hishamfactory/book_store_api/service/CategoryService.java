package com.hishamfactory.book_store_api.service;

import com.hishamfactory.book_store_api.entity.Category;
import com.hishamfactory.book_store_api.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public Category addCategory(Category category){
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new RuntimeException("this category already exists");
        }
        return categoryRepository.save(category);
    }
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
    public Category getCategoryByName(String category_name){
        return categoryRepository.findByName(category_name).orElseThrow(() -> new RuntimeException("book not found"));
    }

    public Category getCategoryByID(Long category_id){
        return categoryRepository.findById(category_id).orElseThrow(() -> new RuntimeException("book not found"));
    }
    @Transactional
    public void deleteCategoryByName(String category_name){
        Optional<Category> optionalCategory = categoryRepository.findByName(category_name);
        if(optionalCategory.isEmpty()){
            throw new RuntimeException("this category doesn't exists.");
        }
        Category category = optionalCategory.get();
        if(category.getBooks() != null && !category.getBooks().isEmpty()){
            throw new RuntimeException("can't delete this category because it's still contains books.");
        }
        categoryRepository.delete(category);
    }

    public Category updateCategory(Long existing_category_id, Category updated_category){
        Category updatedCategory = categoryRepository.findById(existing_category_id).orElseThrow(() -> new RuntimeException("category not found"));
            updatedCategory.setName(updated_category.getName());
            updatedCategory.setDescription(updated_category.getDescription());
            return categoryRepository.save(updatedCategory);
    }
}
