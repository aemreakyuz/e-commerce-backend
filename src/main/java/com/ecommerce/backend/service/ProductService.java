package com.ecommerce.backend.service;


import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getProductById(long id){
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            return productOptional.get();
        }
        throw new IllegalArgumentException("Product with given id not found");
    }

    public List<Product> getProductsByCategory(String categoryName){
        if( productRepository.productsByCategory(categoryName) != null){
            return productRepository.productsByCategory(categoryName);
        }
        throw new IllegalArgumentException("No products found related to the given category name");
    }

    public Product saveProduct(Product product){
        Optional<Product> optionalProduct = productRepository.findByProductName(product.getName());
        if(optionalProduct.isPresent()){
            throw new IllegalArgumentException("Product is already present");
        }
        return productRepository.save(product);
    }
}
