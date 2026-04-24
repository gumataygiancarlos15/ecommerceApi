package com.ws101.gumatay.service;

import com.ws101.gumatay.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private Long nextId = 1L;

    // Initialize with sample data
    public ProductService() {
        productList.add(new Product(nextId++, "Laptop", "Gaming laptop", 999.99, "Electronics", 10, "url1"));
        productList.add(new Product(nextId++, "Mouse", "Wireless mouse", 25.50, "Accessories", 50, "url2"));
        productList.add(new Product(nextId++, "Keyboard", "Mechanical keyboard", 89.99, "Accessories", 30, "url3"));
        productList.add(new Product(nextId++, "Monitor", "24 inch monitor", 199.99, "Electronics", 15, "url4"));
        productList.add(new Product(nextId++, "Headphones", "Noise cancelling", 150.00, "Audio", 20, "url5"));
        productList.add(new Product(nextId++, "Chair", "Ergonomic chair", 180.00, "Furniture", 8, "url6"));
        productList.add(new Product(nextId++, "Desk", "Standing desk", 250.00, "Furniture", 5, "url7"));
        productList.add(new Product(nextId++, "Webcam", "HD webcam", 45.99, "Electronics", 25, "url8"));
        productList.add(new Product(nextId++, "Microphone", "USB mic", 75.00, "Audio", 12, "url9"));
        productList.add(new Product(nextId++, "USB Drive", "128GB storage", 18.50, "Accessories", 100, "url10"));
    }

    // Get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create new product
    public Product createProduct(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    // Update product
    public Product updateProduct(Long id, Product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            Product existing = productList.get(i);
            if (existing.getId().equals(id)) {
                updatedProduct.setId(id);
                productList.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    // Delete product
    public boolean deleteProduct(Long id) {
        return productList.removeIf(product -> product.getId().equals(id));
    }

    // Filter by category
    public List<Product> filterByCategory(String category) {
        return productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    // Filter by price range
    public List<Product> filterByPrice(double minPrice, double maxPrice) {
        return productList.stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .toList();
    }

    // Filter by name
    public List<Product> filterByName(String name) {
        return productList.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}