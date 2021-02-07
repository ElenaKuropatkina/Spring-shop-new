package com.elenakuropatkina.services;

import com.elenakuropatkina.controllers.represents.ProductRepresent;
import com.elenakuropatkina.services.model.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart() {
        assertNotNull(cartService.getOrderItems());
        assertEquals(0, cartService.getOrderItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddOneProduct() {
        ProductRepresent expectedProduct = new ProductRepresent();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(100));
        expectedProduct.setTitle("Product title");

        cartService.addQty(expectedProduct,1);

        List<OrderItem> orderItems = cartService.getOrderItems();
        assertNotNull(orderItems);
        assertEquals(1, orderItems.size());

        OrderItem orderItem = orderItems.get(0);
        assertEquals(1, orderItem.getQty());

        assertEquals(expectedProduct.getId(), orderItem.getProductId());
        assertNotNull(orderItem.getProductRepresent());
        assertEquals(expectedProduct.getTitle(), orderItem.getProductRepresent().getTitle());
    }
}
