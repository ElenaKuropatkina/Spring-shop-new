package com.elenakuropatkina.repositories;


import com.elenakuropatkina.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByTitle(String name);
}
