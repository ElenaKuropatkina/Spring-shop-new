package com.elenakuropatkina.controllers.represents;

import com.elenakuropatkina.models.Role;
import com.elenakuropatkina.models.User;

import javax.validation.constraints.NotEmpty;

import java.util.Set;

public class UserRepresent {

    private Long id;

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Set<Role> roles;

    public UserRepresent() {
    }

    public UserRepresent(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    @Override
    public String toString() {
        return "UserRepresent{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
