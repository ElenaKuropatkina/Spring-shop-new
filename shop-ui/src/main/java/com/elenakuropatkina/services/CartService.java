package com.elenakuropatkina.services;

import com.elenakuropatkina.controllers.represents.ProductRepresent;
import com.elenakuropatkina.services.model.OrderItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public interface CartService {

    void addQty(ProductRepresent productRepresent, int qty);

    void reduceQty(ProductRepresent productRepresent, int qty);

    void removeProduct(ProductRepresent productRepresent);

    List<OrderItem> getOrderItems();

    BigDecimal getSubTotal();

    void updateCart(OrderItem orderItem);
}
