package com.elenakuropatkina.services.model;

import com.elenakuropatkina.controllers.represents.ProductRepresent;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem implements Serializable {

    private Long productId;

    private ProductRepresent productRepresent;

    private Integer qty;

    public OrderItem(ProductRepresent productRepresent) {
        this.productId = productRepresent.getId();
        this.productRepresent = productRepresent;

    }

    public OrderItem() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductRepresent getProductRepresent() {
        return productRepresent;
    }

    public void setProductRepresent(ProductRepresent productRepresent) {
        this.productRepresent = productRepresent;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }


    @JsonIgnore
    public BigDecimal getTotal() {
        return productRepresent.getPrice().multiply(new BigDecimal(qty));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return productId.equals(orderItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
