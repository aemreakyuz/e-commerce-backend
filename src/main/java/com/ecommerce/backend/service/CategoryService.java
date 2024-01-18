package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(long id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            return categoryOptional.get();
        }
        throw new IllegalArgumentException("Product with given id not found");
    }

    public Category saveCategory(Category category){
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if(optionalCategory.isPresent()){
            throw new IllegalArgumentException("Category is already present");
        }
        return categoryRepository.save(category);
    }


}
