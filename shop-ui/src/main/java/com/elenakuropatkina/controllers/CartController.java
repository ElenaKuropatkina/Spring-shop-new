package com.elenakuropatkina.controllers;

import com.elenakuropatkina.services.CartService;
import com.elenakuropatkina.services.ProductService;
import com.elenakuropatkina.services.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping("/cart")
    public String cartPage(Model model) {
        model.addAttribute("orderItems", cartService.getOrderItems());
        model.addAttribute("subTotal", cartService.getSubTotal());
        return "cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String updateCart(OrderItem orderItem) {
        orderItem.setProductRepresent(productService.findById(orderItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.updateCart(orderItem);
        return "redirect:/cart";
    }
}

