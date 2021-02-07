package com.elenakuropatkina.services;

import com.elenakuropatkina.controllers.represents.UserRepresent;
import com.elenakuropatkina.models.User;
import com.elenakuropatkina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserRepresent userRepresent) {
        User user = new User();
        user.setId(userRepresent.getId());
        user.setLogin(userRepresent.getLogin());
        user.setPassword(passwordEncoder.encode(userRepresent.getPassword()));
        user.setFirstName(userRepresent.getFirstName());
        user.setLastName(userRepresent.getLastName());
        user.setEmail(userRepresent.getEmail());
        user.setRoles(userRepresent.getRoles());
        userRepository.save(user);
    }

    @Override
    public List<UserRepresent> findAll() {
        return userRepository.findAll().stream()
                .map(UserRepresent::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRepresent> findById(Long id) {
        return userRepository.findById(id).map(UserRepresent::new);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
