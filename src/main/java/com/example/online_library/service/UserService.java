package com.example.online_library.service;

import com.example.online_library.dto.SaveUserDto;
import com.example.online_library.dto.ViewUserDto;

import java.util.Set;

public interface UserService {
    Set<ViewUserDto> findAll();
    ViewUserDto findById(Long id);
    ViewUserDto findByEmail(String email);
    ViewUserDto save(SaveUserDto saveUserDto);
    ViewUserDto update(Long id, SaveUserDto saveUserDto);
    void deleteById(Long id);
}
