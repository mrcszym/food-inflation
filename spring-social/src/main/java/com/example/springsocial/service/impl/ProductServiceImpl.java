package com.example.springsocial.service.impl;

import com.example.springsocial.model.Product;
import com.example.springsocial.repository.ProductRepository;
import com.example.springsocial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductPricesByName(String name) {
        return productRepository.getProductsByName(name);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }
}
