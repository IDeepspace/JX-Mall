package com.tw.java.controller;

import com.tw.java.entity.Inventory;
import com.tw.java.entity.Product;
import com.tw.java.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(value = "")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Inventory inventory = new Inventory(0,0);

        product.setInventory(inventory);

        Product createdProduct = productRepository.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
