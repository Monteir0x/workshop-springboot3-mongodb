package com.monteirox.workshopmongo.dto;

import com.monteirox.workshopmongo.domain.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Optional<User> user;

    private String id;
    private String name;
    private String email;

    public UserDTO() {
        this(Optional.<User>empty());
    }

    public UserDTO(Optional<User> user){
        this.user = user;
    }

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
