package com.ecommerce.backend.controller;


import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.service.CategoryService;
import com.ecommerce.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Product> getProducts(){
        return productService.getAll();
    }

    @PostMapping("/")
    public Product saveProduct(@RequestBody Product product){
        Product savedProduct = new Product();
        savedProduct.setName(product.getName());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setStock(product.getStock());
        savedProduct.setRating(product.getRating());
        savedProduct.setSellCount(product.getSellCount());
        savedProduct.setImages(product.getImages());
        Category category = categoryService.getCategoryById(product.getCategory().getId());
        savedProduct.setCategory(category);
        category.addProduct(savedProduct);
        savedProduct = productService.saveProduct(savedProduct);
        return savedProduct;
    }


}
