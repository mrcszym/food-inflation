package com.example.springsocial.controller;

import com.example.springsocial.model.Product;
import com.example.springsocial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/id/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/product/name/{name}")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public List<Product> getProductByName(@PathVariable String name) {
        return productService.getProductPricesByName(name);
    }
}
