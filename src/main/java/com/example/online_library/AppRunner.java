package com.example.online_library;

import com.example.online_library.model.Author;
import com.example.online_library.repository.AuthorRepository;
import com.example.online_library.repository.UserRepository;
import com.example.online_library.service.AuthorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    public AppRunner(UserRepository userRepository, AuthorService authorService, AuthorRepository authorRepository) {
        this.userRepository = userRepository;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /*User user = User.builder()
                .firstName("Yana")
                .lastName("Ilieva")
                .password("12345")
                .phoneNumber("0888202284")
                .registrationDate(new Date())
                .build();
        userRepository.save(user);
         */
        Author a1 = Author.builder().firstName("Steven").lastName("King").build();
        Author a2 = Author.builder().firstName("Steven").lastName("Dostoevsky").build();
        authorRepository.save(a1);
        authorRepository.save(a2);
    }
}
