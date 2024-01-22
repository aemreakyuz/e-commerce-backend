package com.ecommerce.backend.repository;


import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductRepositoryTest {

    private ProductRepository productRepository;

    @Autowired

    public ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private void createProduct(String productName){
        Product product = new Product();
        product.setCategory(new Category());
        product.setDescription("kazakk");
        product.setImages(new String[]{"https://picsum.photos/200/300"});
        product.setName(productName);
        product.setPrice(150);
        product.setRating(5.0);
        product.setSellCount(125);
        product.setStock(500);
        Optional<Product> foundProduct = productRepository.findByProductName(productName);
        if(foundProduct.isEmpty()){
            productRepository.save(product);
        }
    }

    @BeforeEach
    void setUp(){
        createProduct("Bol Kazak");
    }

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }

    @DisplayName("Can find product by its name")
    @Test
    void findByName(){
        Optional<Product> foundProductOptional = productRepository.findByProductName("Bol Kazak");
        assertTrue(foundProductOptional.isPresent(), "Product not found");

        Product foundProduct = foundProductOptional.get();
        assertEquals("Bol Kazak", foundProduct.getName());
    }

    @DisplayName("Can't find product by its name")
    @Test
    void findByTcknFail() {
        Optional<Product> foundProductOptional = productRepository.findByProductName("Ekstra Bol Kazak");
        assertFalse(foundProductOptional.isPresent(), "Product should not be found");

    }

}
