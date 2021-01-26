package com.example.online_library.service;

import com.example.online_library.model.User;

public interface UserService {
    User findById(Long id);
    User save(User user);
}
