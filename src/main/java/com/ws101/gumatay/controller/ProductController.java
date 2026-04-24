package com.ws101.gumatay.estrada.gallano.controller;

import com.ws101.gumatay.estrada.gallano.model.Product;
import com.ws101.gumatay.estrada.gallano.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST create new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT update entire product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH partial update
    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Product existing = productService.getProductById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        // Apply updates
        if (updates.containsKey("name")) {
            existing.setName((String) updates.get("name"));
        }
        if (updates.containsKey("description")) {
            existing.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("price")) {
            existing.setPrice((Double) updates.get("price"));
        }
        if (updates.containsKey("category")) {
            existing.setCategory((String) updates.get("category"));
        }
        if (updates.containsKey("stockQuantity")) {
            existing.setStockQuantity((Integer) updates.get("stockQuantity"));
        }
        if (updates.containsKey("imageUrl")) {
            existing.setImageUrl((String) updates.get("imageUrl"));
        }

        Product updated = productService.updateProduct(id, existing);
        return ResponseEntity.ok(updated);
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // FILTER products
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        List<Product> result;

        switch (filterType.toLowerCase()) {
            case "category":
                result = productService.filterByCategory(filterValue);
                break;
            case "name":
                result = productService.filterByName(filterValue);
                break;
            case "price":
                // Expect value like "min-max"
                String[] range = filterValue.split("-");
                double min = Double.parseDouble(range[0]);
                double max = Double.parseDouble(range[1]);
                result = productService.filterByPrice(min, max);
                break;
            default:
                return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(result);
    }
}