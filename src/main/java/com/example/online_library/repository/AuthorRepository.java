package com.example.online_library.repository;

import com.example.online_library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Set<Author> findByFirstName(String name);
    Set<Author> findByLastName(String name);
}
