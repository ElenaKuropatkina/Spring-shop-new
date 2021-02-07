package com.elenakuropatkina.services;

import com.elenakuropatkina.controllers.represents.ProductRepresent;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ProductService extends Serializable {

    Optional<ProductRepresent> findById(Long id);

    List<ProductRepresent> findAll();

}
