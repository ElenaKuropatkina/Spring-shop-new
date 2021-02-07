package com.elenakuropatkina.controllers.represents;

import com.elenakuropatkina.models.Author;
import com.elenakuropatkina.models.Category;
import com.elenakuropatkina.models.Picture;
import com.elenakuropatkina.models.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductRepresent implements Serializable {

    private Long id;

    private String title;

    private BigDecimal price;

    private Category category;

    private Author author;

    private List<Long> picturesId;

    private Long mainPictureId;


    public ProductRepresent() {
    }

    public ProductRepresent(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.author = product.getAuthor();
        this.picturesId = product.getPictures().stream()
                .map(Picture::getId)
                .collect(Collectors.toList());
        this.mainPictureId = picturesId.isEmpty() ? -1L : picturesId.get(0);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Long> getPicturesId() {
        return picturesId;
    }

    public void setPicturesId(List<Long> picturesId) {
        this.picturesId = picturesId;
    }

    public Long getMainPictureId() {
        return mainPictureId;
    }

    public void setMainPictureId(Long mainPictureId) {
        this.mainPictureId = mainPictureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepresent that = (ProductRepresent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
