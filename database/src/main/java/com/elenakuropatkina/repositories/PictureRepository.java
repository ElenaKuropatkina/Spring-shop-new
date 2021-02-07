package com.elenakuropatkina.repositories;

import com.elenakuropatkina.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
