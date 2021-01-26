package com.example.online_library.repository;

import com.example.online_library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByFirstName(String name);
    Optional<Author> findByLastName(String name);
}
