package com.monteirox.workshopmongo.services;

import com.monteirox.workshopmongo.domain.User;
import com.monteirox.workshopmongo.dto.UserDTO;
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

    public User insert(User user){
        return userRepository.insert(user);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }
    public User update(User user){
        User newUser = userRepository.findAll().get(Integer.parseInt(user.getId()));
        updateData(newUser, user);
        return userRepository.save(newUser);
    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}