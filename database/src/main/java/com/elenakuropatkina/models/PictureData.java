package com.elenakuropatkina.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pictures_data")
public class PictureData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_title")
    private String fileTitle;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "data", length = 33554430)
    private byte[] data;

    public PictureData() {
    }

    public PictureData(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public PictureData(byte[] data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}