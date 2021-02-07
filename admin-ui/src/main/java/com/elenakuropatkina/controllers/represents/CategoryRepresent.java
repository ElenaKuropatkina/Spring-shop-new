package com.elenakuropatkina.controllers.represents;

import com.elenakuropatkina.models.Category;

import java.io.Serializable;


public class CategoryRepresent implements Serializable {

    private long id;

    private String title;

    private long productCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }

    public CategoryRepresent(long id, String title, long productCount) {
        this.id = id;
        this.title = title;
        this.productCount = productCount;
    }

    public CategoryRepresent(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }

}
