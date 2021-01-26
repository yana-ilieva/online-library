package com.example.online_library.service;

import com.example.online_library.exception.DuplicateRecordException;
import com.example.online_library.exception.RecordNotFoundException;
import com.example.online_library.model.User;
import com.example.online_library.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public User save(User user) {
        try{
            user.setId(null);
            user.setRegistrationDate(new Date());
            return userRepository.save(user);
        } catch(DataIntegrityViolationException e){
            throw new DuplicateRecordException("User with email " + user.getEmail() + " already exists.");
        }
    }
}
