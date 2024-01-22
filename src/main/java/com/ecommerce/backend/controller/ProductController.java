package com.ecommerce.backend.controller;


import com.ecommerce.backend.dto.ProductRequest;
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
    public Product saveProduct(@RequestBody ProductRequest productRequest){

        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        product.setRating(productRequest.rating());
        product.setSellCount(productRequest.sellCount());
        product.setStock(productRequest.stock());
        product.setImages(productRequest.images());
        Category category = categoryService.getCategoryById(productRequest.categoryId());
        product.setCategory(category);
        category.addProduct(product);

        return productService.saveProduct(product);
    }

}
