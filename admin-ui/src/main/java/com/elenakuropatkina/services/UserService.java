package com.elenakuropatkina.services;

import com.elenakuropatkina.controllers.represents.UserRepresent;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserRepresent> findAll();

    Optional<UserRepresent> findById(Long id);

    void save(UserRepresent userRepresent);

    void delete(Long id);
}
