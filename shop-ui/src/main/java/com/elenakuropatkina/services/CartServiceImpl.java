package com.elenakuropatkina.services;

import com.elenakuropatkina.controllers.represents.ProductRepresent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import com.elenakuropatkina.services.model.OrderItem;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

    //private static final long serialVersionUID = -9025621122549454991L;

    private Map<OrderItem, Integer> orderItems;

    //private BigDecimal totalCost;

    public CartServiceImpl() {
        this.orderItems = new HashMap<>();
    }

    public CartServiceImpl(List<OrderItem> orderItems) {
        this.orderItems = orderItems.stream().collect(Collectors.toMap(oi -> oi, OrderItem::getQty));
    }

    @Override
    public void addQty(ProductRepresent productRepresent, int qty) {
        OrderItem orderItem = new OrderItem(productRepresent);
        orderItems.put(orderItem, orderItems.getOrDefault(orderItem, 0) + qty);
    }

    @Override
    public void reduceQty(ProductRepresent productRepresent, int qty) {
        OrderItem orderItem = new OrderItem(productRepresent);
        int currentQty = orderItems.getOrDefault(orderItem, 0);
        if (currentQty - qty > 0) {
            orderItems.put(orderItem, currentQty - qty);
        } else {
            orderItems.remove(orderItem);
        }
    }


    @Override
    public void removeProduct(ProductRepresent productRepresent) {
        orderItems.remove(new OrderItem(productRepresent));
    }


        @Override
    public List<OrderItem> getOrderItems() {
        orderItems.forEach(OrderItem::setQty);
        return new ArrayList<>(orderItems.keySet());
    }

    @Override
    public BigDecimal getSubTotal() {
        orderItems.forEach(OrderItem::setQty);
        return orderItems.keySet().stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void updateCart(OrderItem orderItem) {
        orderItems.put(orderItem, orderItem.getQty());
    }
}
