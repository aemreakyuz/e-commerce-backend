package com.ecommerce.backend.controller;


import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.service.CategoryService;
import com.ecommerce.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public CategoryController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Category> getAll(){
      return categoryService.getAll();
    }

    @PostMapping("/")
    public Category addCategory(@RequestBody Category category){
        Category savedCategory = new Category();
        savedCategory.setGender(category.getGender());
        savedCategory.setImage(category.getImage());
        savedCategory.setCode(category.getCode());
        savedCategory.setRating(category.getRating());
        savedCategory.setTitle(category.getTitle());
        savedCategory = categoryService.saveCategory(category);
        return savedCategory;
    }
}
