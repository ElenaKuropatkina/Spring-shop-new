package com.elenakuropatkina.services;

import com.elenakuropatkina.controllers.represents.ProductRepresent;
import com.elenakuropatkina.models.Product;
import com.elenakuropatkina.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<ProductRepresent> findById(Long id) {
        return productRepository.findById(id).map(ProductRepresent::new);
    }

    @Override
    public List<ProductRepresent> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepresent::new)
                .collect(Collectors.toList());
    }
}

