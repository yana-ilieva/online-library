package com.example.online_library.controller;

import com.example.online_library.dto.GenreDto;
import com.example.online_library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<Set<GenreDto>> findAll(){
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(genreService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<GenreDto> findByName(@PathVariable String name){
        return ResponseEntity.ok(genreService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<GenreDto> save(@RequestBody @Valid GenreDto genreDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.save(genreDto));
    }

    @PutMapping
    public ResponseEntity<GenreDto> update(@RequestBody @Valid GenreDto genreDto){
        return ResponseEntity.ok(genreService.update(genreDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        genreService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
