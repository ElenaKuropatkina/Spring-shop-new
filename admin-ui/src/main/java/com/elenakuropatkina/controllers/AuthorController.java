package com.elenakuropatkina.controllers;

import com.elenakuropatkina.models.Author;
import com.elenakuropatkina.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;

    }

    @GetMapping("/authors")
    public String authorsPage(Model model) {
        model.addAttribute("activePage", "Authors");
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

    @GetMapping("/author/{id}/edit")
    public String editAuthor(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Authors");
        model.addAttribute("author", authorRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "author_form";
    }

    @GetMapping("/author/create")
    public String createAuthor(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Authors");
        model.addAttribute("author", new Author());
        return "author_form";
    }

    @DeleteMapping("/author/{id}/delete")
    public String deleteAuthor(Model model, @PathVariable("id") Long id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }

    @PostMapping("/author")
    public String upsertAuthor(Model model, RedirectAttributes redirectAttributes, Author author) {
        model.addAttribute("activePage", "Authors");

        try {
            authorRepository.save(author);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", true);
            if (author.getId() == null) {
                return "redirect:/author/create";
            }
            return "redirect:/author/" + author.getId() + "/edit";
        }
        return "redirect:/authors";
    }


}