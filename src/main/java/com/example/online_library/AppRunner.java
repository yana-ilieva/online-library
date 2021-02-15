package com.example.online_library;

import com.example.online_library.dto.RoleDto;
import com.example.online_library.model.User;
import com.example.online_library.repository.AuthorRepository;
import com.example.online_library.repository.UserRepository;
import com.example.online_library.service.AuthorService;
import com.example.online_library.service.BookService;
import com.example.online_library.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final RoleService roleService;
    private final BookService bookService;

    public AppRunner(UserRepository userRepository, AuthorService authorService, AuthorRepository authorRepository, RoleService roleService, BookService bookService) {
        this.userRepository = userRepository;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
        this.roleService = roleService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .firstName("Yana")
                .lastName("Ilieva")
                .email("yana@example.com")
                .password("12345")
                .phoneNumber("0888202284")
                .registrationDate(new Date())
                .build();
        User user2 = User.builder()
                .firstName("Mariyan")
                .lastName("Zhelyazkov")
                .email("mar@example.com")
                .password("12345")
                .phoneNumber("0888202000")
                .registrationDate(new Date())
                .build();
        userRepository.save(user);
        userRepository.save(user2);

        roleService.save(RoleDto.builder().name("ROLE_READER").build());



       /*
        Author a1 = Author.builder().firstName("Steven").lastName("King").build();
        Author a2 = Author.builder().firstName("Steven").lastName("Dostoevsky").build();
        authorRepository.save(a1);
        authorRepository.save(a2);

        */
    }
}
