package com.elenakuropatkina.services;

import com.elenakuropatkina.models.PictureData;
import com.elenakuropatkina.models.Product;

import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);

    Optional<Product> getProductByPictureId(long id);

    void removePicture(long id);
}