package com.ecommerce.backend.converter;

import com.ecommerce.backend.dto.ProductRequest;
import com.ecommerce.backend.entity.Product;

public class DtoConverter {


    public static ProductRequest convertToProductRequest(Product product) {
        return new ProductRequest(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getRating(),
                product.getSellCount(),
                product.getStock(),
                product.getImages(),
               product.getCategory().getId()
        );
    }
}
