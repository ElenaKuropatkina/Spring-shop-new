package com.elenakuropatkina.controllers;

import com.elenakuropatkina.controllers.represents.ProductRepresent;
import com.elenakuropatkina.exeptions.NotFoundException;
import com.elenakuropatkina.repositories.AuthorRepository;
import com.elenakuropatkina.repositories.CategoryRepository;
import com.elenakuropatkina.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class ProductController {

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    @Autowired
    public ProductController(AuthorRepository authorRepository, CategoryRepository categoryRepository,
                             ProductService productService) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @GetMapping("/products")
    public String productsPage(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/product/{id}/edit")
    public String editProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", productService.findById(id).orElseThrow(() -> new NotFoundException()));
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_form";
    }

    @GetMapping("/product/create")
    public String createProduct(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", new ProductRepresent());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_form";
    }

    @PostMapping("/product")
    public String upsertProduct(Model model, RedirectAttributes redirectAttributes, ProductRepresent product) throws IOException {
        model.addAttribute("activePage", "Products");

        try {
            productService.save(product);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", true);
            if (product.getId() == null) {
                return "redirect:/product/create";
            }
            return "redirect:/product/" + product.getId() + "/edit";
        }
        return "redirect:/products";
    }

    @DeleteMapping("/product/{id}/delete")
    public String deleteProduct(Model model, @PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}





