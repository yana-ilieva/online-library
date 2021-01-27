package com.example.online_library.service;

import com.example.online_library.dto.GenreDto;

import java.util.Set;

public interface GenreService {
    Set<GenreDto> findAll();
    GenreDto findById(Long id);
    GenreDto findByName(String name);
    GenreDto save(GenreDto genreDto);
    GenreDto update(GenreDto genreDto);
    void deleteById(Long id);
}
