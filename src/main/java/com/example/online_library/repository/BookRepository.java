package com.example.online_library.repository;

import com.example.online_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByInventoryNumber(Long id);
    Set<Book> findByIsbn(Long id);
}
