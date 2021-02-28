package com.example.online_library;

import com.example.online_library.dto.RoleDto;
import com.example.online_library.model.*;
import com.example.online_library.repository.*;
import com.example.online_library.service.AuthorService;
import com.example.online_library.service.BookService;
import com.example.online_library.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class AppRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final RoleService roleService;
    private final BookService bookService;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    public AppRunner(UserRepository userRepository, AuthorService authorService, AuthorRepository authorRepository, RoleService roleService, BookService bookService, GenreRepository genreRepository, PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
        this.roleService = roleService;
        this.bookService = bookService;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /*
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
*/
        roleService.save(RoleDto.builder().name("ROLE_READER").build());
/*
        Author a1 = Author.builder().firstName("Steven").lastName("King").build();
        Author a2 = Author.builder().firstName("Steven").lastName("Dostoevsky").build();
        Author A1 = authorRepository.save(a1);
        Author A2 =authorRepository.save(a2);

        Set<Author> aSet = new HashSet<>();
        aSet.add(A1);
        aSet.add(A2);

        Genre g = genreRepository.save(Genre.builder().name("Fantasy").build());

        Publisher p = publisherRepository.save(Publisher.builder().name("Coleman").build());

        Book b1 = Book.builder()
                .inventoryNumber(1454745L)
                .title("A great story of Neverland")
                .authors(aSet)
                .isbn("12348743102")
                .genre(g)
                .publisher(p)
                .issueDate(new Date())
                .build();

        Set<Author> bSet = new HashSet<>();
        bSet.add(A1);
        Book b2 = Book.builder()
                .inventoryNumber(1568475L)
                .title("Stories of Old Times")
                .authors(bSet)
                .isbn("12354684102")
                .genre(g)
                .publisher(p)
                .issueDate(new Date())
                .build();

        bookRepository.save(b1);
        bookRepository.save(b2);
*/
    }
}
