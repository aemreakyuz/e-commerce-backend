package com.ecommerce.backend.dto;

public record ProductRequest(String name, String description, Double price, Double rating,
                             Integer sellCount, Integer stock, String[] images, Long categoryId) {
}
