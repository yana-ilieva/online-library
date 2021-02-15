package com.example.online_library.service;

import com.example.online_library.dto.SaveUserDto;
import com.example.online_library.dto.UpdateBookDto;
import com.example.online_library.dto.ViewBookDto;

import java.util.Set;

public interface BookService {

    Set<ViewBookDto> findAll();
    ViewBookDto findById(Long id);
    Set<ViewBookDto> findByTitle(String title);
    ViewBookDto findByInventoryNumber(Long number);
    Set<ViewBookDto> findByIsbn(Long id);
    Set<ViewBookDto> findByAuthor(String name);
    Set<ViewBookDto> findByGenre(String genre);
    Set<ViewBookDto> findByPublisher(String publisher);
    ViewBookDto save(SaveUserDto saveUserDto);
    ViewBookDto update(UpdateBookDto updateBookDto);
    void deleteById(Long id);
}
