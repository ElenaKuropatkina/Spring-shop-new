package com.elenakuropatkina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/admin")
    public String showHomePage(Model model) {
        model.addAttribute("activePage", "None");
        return "index";
    }

}