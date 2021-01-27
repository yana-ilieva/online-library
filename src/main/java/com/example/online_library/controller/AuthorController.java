package com.example.online_library.controller;

import com.example.online_library.dto.AuthorDto;
import com.example.online_library.dto.UpdateAuthorDto;
import com.example.online_library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.findById(id));
    }

    @GetMapping(value = "/firstName/{name}")
    public ResponseEntity<Set<AuthorDto>> findByFirstName(@PathVariable String name){
        return ResponseEntity.ok(authorService.findByFirstName(name));
    }

    @GetMapping(value = "/lastName/{name}")
    public ResponseEntity<Set<AuthorDto>> findByLastName(@PathVariable String name){
        return ResponseEntity.ok(authorService.findByLastName(name));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> save(@RequestBody @Valid AuthorDto authorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.save(authorDto));
    }

    @PutMapping
    public ResponseEntity<AuthorDto> update(@RequestBody @Valid UpdateAuthorDto updateAuthorDto){
        return ResponseEntity.ok(authorService.update(updateAuthorDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
