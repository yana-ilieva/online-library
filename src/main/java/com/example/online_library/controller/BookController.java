package com.example.online_library.controller;

import com.example.online_library.dto.ViewBookDto;
import com.example.online_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Set<ViewBookDto>> findAll(){
        return ResponseEntity.ok(bookService.findAll());
    }
}
