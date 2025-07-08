package com.hishamfactory.book_store_api.service;

import com.hishamfactory.book_store_api.entity.Category;
import com.hishamfactory.book_store_api.repository.CategoryRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public void addCategory(Category category){
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new RuntimeException("this category already exists");
        }
        categoryRepository.save(category);
    }
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
    public Category getCategoryByName(String category_name){
        return categoryRepository.findByName(category_name).orElseThrow(() -> new RuntimeException("book not found"));
    }
//    public void deleteCategoryByName(String category_name){
//        Optional<Category> category = categoryRepository.findByName(category_name);
//        if(!category.stream().toList().isEmpty()){
//            throw new RuntimeException("can't delete this category due still contains books.");
//        }else if(category.isEmpty()){
//            throw new RuntimeException("this category doesn't exists.");
//        }
//        categoryRepository.delete(category);
//    }
}
