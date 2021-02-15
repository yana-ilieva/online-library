package com.example.online_library.service;

import com.example.online_library.dto.*;
import com.example.online_library.exception.DuplicateRecordException;
import com.example.online_library.exception.RecordNotFoundException;
import com.example.online_library.model.Role;
import com.example.online_library.model.User;
import com.example.online_library.repository.UserRepository;

import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<ViewUserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, ViewUserDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public ViewUserDto findById(@NonNull Long id){
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, ViewUserDto.class))
                .orElseThrow(() -> new RecordNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public ViewUserDto findByEmail(@NonNull String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, ViewUserDto.class))
                .orElseThrow(() -> new RecordNotFoundException("User with email " + email + " not found."));
    }

    @Override
    public ViewUserDto save(@NonNull SaveUserDto saveUserDto) {
        try{
            User user = modelMapper.map(saveUserDto, User.class);
            user.setRegistrationDate(new Date());
            user.setPassword("12345");

            RoleDto roleDto = roleService.findByName("ROLE_READER");
            Role reader = modelMapper.map(roleDto, Role.class);

            user.setRole(reader);

            User saved = userRepository.save(user);
            return modelMapper.map(saved, ViewUserDto.class);
        } catch(DataIntegrityViolationException e){
            throw new DuplicateRecordException("User with email " + saveUserDto.getEmail() + " already exists.");
        }
    }

    @Override
    public ViewUserDto update(@NotNull Long id, @NonNull SaveUserDto saveUserDto) {
        try{
            Optional<User> maybeUser = userRepository.findById(id);
            if(maybeUser.isPresent()){
                modelMapper.map(saveUserDto, maybeUser.get());
                User saved = userRepository.save(maybeUser.get());
                return modelMapper.map(saved, ViewUserDto.class);
            } else {
                throw new NoSuchElementException();
            }
        } catch(NoSuchElementException e){
            throw new RecordNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public void deleteById(@NonNull Long id) {
        try{
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new RecordNotFoundException("User with id " + id + " not found.");
        }
    }
}
