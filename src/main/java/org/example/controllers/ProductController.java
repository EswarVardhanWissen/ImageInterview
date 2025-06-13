package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.Product;
import org.example.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllCatagories() {
        List<Product> listOfProducts = productRepository.findAll();
        return ResponseEntity.ok(listOfProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getCatagoryByID(@PathVariable Integer id) {
        Product getProductByID = productRepository.findById(id).orElse(null);
        if (getProductByID != null) {
            return ResponseEntity.ok(getProductByID);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createCatagory(@RequestBody Product product) {
        Product newProduct = Product.builder()
                .skuCode(product.getSkuCode())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .status(product.getStatus())
                .catagoryId(product.getCatagoryId())
                .build();
        productRepository.save(newProduct);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCatagory(@PathVariable Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Successfully Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
