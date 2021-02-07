package com.elenakuropatkina.controllers;

import com.elenakuropatkina.controllers.represents.UserRepresent;
import com.elenakuropatkina.exeptions.NotFoundException;
import com.elenakuropatkina.repositories.RoleRepository;
import com.elenakuropatkina.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserController {

    private final RoleRepository roleRepository;

    private final UserService userService;

    @Autowired
    public UserController(RoleRepository roleRepository,
                          UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userService.findById(id).orElseThrow(() -> new NotFoundException()));
        model.addAttribute("roles", roleRepository.findAll());
        return "user_edit_form";
    }

    @GetMapping("/user/create")
    public String createUser(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new UserRepresent());
        model.addAttribute("roles", roleRepository.findAll());
        return "user_edit_form";
    }

    @PostMapping("/user")
    public String upsertUser(@Valid UserRepresent user, Model model, BindingResult bindingResult) throws IOException {
        model.addAttribute("activePage", "Users");

        if (bindingResult.hasErrors()) {
            return "user_edit_form";
        }

        userService.save(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/roles")
    public String rolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleRepository.findAll());
        return "roles";
    }


}