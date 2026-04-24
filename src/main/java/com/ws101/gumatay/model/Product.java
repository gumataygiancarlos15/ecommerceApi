package com.ws101.gumatay.estrada.gallano.EcommerceApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NotBlank(message = "Name is required")
@Size(min = 2, message = "Name must be at least 2 characters")
private String name;

@Positive(message = "Price must be positive number")
private double price;

@NotBlank(message = "Category is required")
private String category;

@Min(value = 0, message = "Stock cannot be negative")
private int stockQuantity;
public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stockQuantity;
    private String imageUrl;
}