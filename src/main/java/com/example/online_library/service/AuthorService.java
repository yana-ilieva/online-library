package com.example.online_library.service;

import com.example.online_library.dto.AuthorDto;
import com.example.online_library.dto.UpdateAuthorDto;

import java.util.Set;

public interface AuthorService {
    Set<AuthorDto> findAll();
    AuthorDto findById(Long id);
    Set<AuthorDto> findByFirstName(String name);
    Set<AuthorDto> findByLastName(String name);
    AuthorDto save(AuthorDto authorDto);
    AuthorDto update(UpdateAuthorDto updateAuthorDto);
    void deleteById(Long id);
}
