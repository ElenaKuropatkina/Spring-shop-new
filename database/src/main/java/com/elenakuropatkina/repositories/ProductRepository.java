package com.elenakuropatkina.repositories;


import com.elenakuropatkina.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
