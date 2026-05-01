package com.ws101.gumatay.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Relationships present are ManyToOne and OneToMany
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, message = "Name must be at least 2 characters")
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Positive(message = "Price must be positive number")
    @Column(nullable = false)
    private double price;

    @NotBlank(message = "Category is required")
    private String imageUrl;

    @Min(value = 0, message = "Stock cannot be negative")
    @Column(nullable = false)
    private int stockQuantity;

    /**
     * @ManyToOne: many products can belong to one category.
     * ex: Different kind of Subjects in one course.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * @OneToMany: one product can be present in multiple orders.
     * ex: The newest apple model bought by multiple people.
     */

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new List<OrderItem>();
}