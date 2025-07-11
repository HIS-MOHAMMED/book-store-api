package com.hishamfactory.book_store_api.controller;

import com.hishamfactory.book_store_api.entity.Book;
import com.hishamfactory.book_store_api.entity.Category;
import com.hishamfactory.book_store_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/getCategories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categoryList = categoryService.getCategories();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        try{
            Category addedCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(addedCategory);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getCategoryByID/{category_id}")
    public ResponseEntity<Category> getCategoryByID(@PathVariable Long category_id){
        Category category = categoryService.getCategoryByID(category_id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/updateCategory/{category_id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long category_id, @RequestBody Category updated_category){
        Category updatedCategory = categoryService.updateCategory(category_id, updated_category);
        return ResponseEntity.ok(updatedCategory);
    }
    @DeleteMapping("/deleteCategory/{category_id}")
    public String deleteCategoryByID(@PathVariable Long category_id){
        Category category = categoryService.getCategoryByID(category_id);
        try{
            categoryService.deleteCategoryByName(category.getName());

        }catch (RuntimeException e){
            return e.getMessage();
        }
        return "the " + category.getName() + " was deleted.";
    }
}
