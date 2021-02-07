package com.elenakuropatkina.services;

import com.elenakuropatkina.controllers.represents.ProductRepresent;
import com.elenakuropatkina.exeptions.NotFoundException;
import com.elenakuropatkina.models.Picture;
import com.elenakuropatkina.models.Product;
import com.elenakuropatkina.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService, Serializable {

    private final ProductRepository productRepository;

    private final PictureService pictureService;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PictureService pictureService) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
    }

    @Override
    @Transactional
    public List<ProductRepresent> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepresent::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ProductRepresent> findById(Long id) {
        return productRepository.findById(id).map(ProductRepresent::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(ProductRepresent productRepresent) throws IOException {
        Product product = (productRepresent.getId() != null) ? productRepository.findById(productRepresent.getId())
                .orElseThrow(NotFoundException::new) : new Product();
        product.setTitle(productRepresent.getTitle());
        product.setCategory(productRepresent.getCategory());
        product.setAuthor(productRepresent.getAuthor());
        product.setPrice(productRepresent.getPrice());

        if (productRepresent.getNewPictures() != null) {
            for (MultipartFile newPicture : productRepresent.getNewPictures()) {

                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes()),
                        product
                ));
            }
        }

        productRepository.save(product);
    }
}

