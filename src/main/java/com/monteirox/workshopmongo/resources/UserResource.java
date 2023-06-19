package com.monteirox.workshopmongo.resources;

import com.monteirox.workshopmongo.domain.User;
import com.monteirox.workshopmongo.services.UserService;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        try {
            List<User> users = userService.findAll();
            return ResponseEntity.ok().body(users);
        }catch (UncategorizedMongoDbException e) {
            e.getMostSpecificCause();
        }
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user = userService.saveUser(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
       return ResponseEntity.created(uri).body(user);
    }
}
