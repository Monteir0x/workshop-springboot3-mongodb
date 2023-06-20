package com.monteirox.workshopmongo.services;

import com.monteirox.workshopmongo.domain.Post;
import com.monteirox.workshopmongo.domain.User;
import com.monteirox.workshopmongo.repository.PostRepository;
import com.monteirox.workshopmongo.repository.UserRepository;
import com.monteirox.workshopmongo.services.exception.ObjectNotFoundException;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;

    }


    public Optional<Post> findById(String id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return post;
    }

}