package com.elenakuropatkina.controllers.represents;

import com.elenakuropatkina.models.Picture;

import java.io.Serializable;

public class PictureRepresent implements Serializable {

    private long id;

    private String title;

    private String contentType;

    public PictureRepresent(Picture picture) {
        this.id = picture.getId();
        this.title = picture.getTitle();
        this.contentType = picture.getContentType();
    }

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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
