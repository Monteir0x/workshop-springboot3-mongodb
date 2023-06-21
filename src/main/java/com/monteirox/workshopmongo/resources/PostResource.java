package com.monteirox.workshopmongo.resources;

import com.monteirox.workshopmongo.domain.Post;
import com.monteirox.workshopmongo.domain.User;
import com.monteirox.workshopmongo.dto.UserDTO;
import com.monteirox.workshopmongo.resources.util.URL;
import com.monteirox.workshopmongo.services.PostService;
import com.monteirox.workshopmongo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Optional<Post> post = postService.findById(id);
        return ResponseEntity.ok().body(post.get());
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodePAram(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }
}
