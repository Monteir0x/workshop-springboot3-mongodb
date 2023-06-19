package com.monteirox.workshopmongo.services;

import com.monteirox.workshopmongo.domain.User;
import com.monteirox.workshopmongo.repository.UserRepository;
import com.monteirox.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
       return userRepository.findAll();
    }

    public Optional<User> findById(String id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}