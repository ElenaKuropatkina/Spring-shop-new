package com.elenakuropatkina.repositories;

import com.elenakuropatkina.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
