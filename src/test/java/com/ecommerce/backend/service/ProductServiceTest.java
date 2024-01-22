package com.ecommerce.backend.service;


import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp(){
        productService = new ProductService(productRepository);
    }

    @Test
    void findAllProducts(){
        productService.getAll();
        verify(productRepository).findAll();
    }

    @Test
    void canSave(){
        Product product = new Product();
        product.setCategory(new Category());
        product.setDescription("kazakk");
        product.setImages(new String[]{"https://picsum.photos/200/300"});
        product.setName("Sweatshirt");
        product.setPrice(150);
        product.setRating(5.0);
        product.setSellCount(125);
        product.setStock(500);
        productService.saveProduct(product);
        verify(productRepository).save(product);
    }
}
