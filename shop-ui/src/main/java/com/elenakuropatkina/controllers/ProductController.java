package com.elenakuropatkina.controllers;

import com.elenakuropatkina.repositories.AuthorRepository;
import com.elenakuropatkina.repositories.CategoryRepository;
import com.elenakuropatkina.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    private final ProductService productService;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductService productService, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping({"/", "/index", "/products"})
    public String indexPage(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "products";
    }

    @RequestMapping("/product_page/{id}")
    public String productPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow(IllegalArgumentException::new));
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_page";
    }
}

