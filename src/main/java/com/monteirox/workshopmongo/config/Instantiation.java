package com.monteirox.workshopmongo.config;

import com.monteirox.workshopmongo.domain.Post;
import com.monteirox.workshopmongo.domain.User;
import com.monteirox.workshopmongo.dto.AuthorDTO;
import com.monteirox.workshopmongo.repository.PostRepository;
import com.monteirox.workshopmongo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Instantiation(UserRepository userRepository,
                         PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();


        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria,alex, bob));

        Post post1 = new Post(null, simpleDateFormat.parse("21/03/2023"), "Partiu viagem", "Vou viajar para São Paulo, abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, simpleDateFormat.parse("23/03/2023"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
