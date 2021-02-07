package com.elenakuropatkina.services;

import com.elenakuropatkina.models.Picture;
import com.elenakuropatkina.models.PictureData;
import com.elenakuropatkina.models.Product;
import com.elenakuropatkina.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

import static java.nio.file.Paths.get;

@Service
@ConditionalOnProperty(name = "picture.storage.type", havingValue = "file")
public class PictureServiceImplFile implements PictureService {

    @Value("${picture.storage.path}")
    private String storagePath;

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImplFile(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return pictureRepository.findById(id)
                .filter(pic -> pic.getPictureData().getFileTitle() != null)
                .map(Picture::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {
        return pictureRepository.findById(id)
                .filter(pic -> pic.getPictureData().getFileTitle() != null)
                .map(pic -> get(storagePath, pic.getPictureData().getFileTitle()))
                .filter(Files::exists)
                .map(path -> {
                    try {
                        return Files.readAllBytes(path);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        String fileTitle = UUID.randomUUID().toString();
        try (OutputStream os = Files.newOutputStream(get(storagePath, fileTitle))) {
            os.write(picture);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return new PictureData(fileTitle);
    }

    @Override
    public Optional<Product> getProductByPictureId(long id) {
        return pictureRepository.findById(id)
                .map(Picture::getProduct);
    }

    @Override
    @Transactional
    public void removePicture(long id) {
        Optional<Picture> opt = pictureRepository.findById(id);
        if (opt.isPresent()) {
            Picture picture = opt.get();
            try {
                Files.delete(get(storagePath, picture.getPictureData().getFileTitle()));
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
            pictureRepository.delete(picture);
        }
    }
}
