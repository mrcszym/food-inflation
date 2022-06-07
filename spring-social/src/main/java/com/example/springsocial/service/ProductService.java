package com.example.springsocial.service;

import com.example.springsocial.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getProductPricesByName(String name);
    public Optional<Product> getProduct(Long id);
}
