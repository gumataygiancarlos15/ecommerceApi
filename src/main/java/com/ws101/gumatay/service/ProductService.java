package com.ws101.gumatay.service;

import com.ws101.gumatay.model.Product;
import com.ws101.gumatay.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Create new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Update product
    public Product updateProduct(Long id, Product updatedProduct) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }

    // Delete product
    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    // Filter by category
    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    // Filter by price range
    public List<Product> filterByPrice(double minPrice, double maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }

    // Filter by name
    public List<Product> filterByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}