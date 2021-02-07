package com.elenakuropatkina.controllers;

import com.elenakuropatkina.models.Category;
import com.elenakuropatkina.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    @GetMapping("/categories")
    public String categoriesPage(Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @GetMapping("/category/{id}/edit")
    public String EditCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", categoryRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "category_form";
    }

    @GetMapping("/category/create")
    public String createCategory(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", new Category());
        return "category_form";
    }

    @DeleteMapping("/category/{id}/delete")
    public String deleteCategory(Model model, @PathVariable("id") Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }


    @PostMapping("/category")
    public String upsertCategory(Model model, RedirectAttributes redirectAttributes, Category category) {
        model.addAttribute("activePage", "Categories");

        try {
            categoryRepository.save(category);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", true);
            if (category.getId() == null) {
                return "redirect:/category/create";
            }
            return "redirect:/category/" + category.getId() + "/edit";
        }
        return "redirect:/categories";
    }

}