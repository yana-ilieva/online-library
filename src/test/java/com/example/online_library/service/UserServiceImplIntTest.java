package com.example.online_library.service;

import com.example.online_library.dto.SaveUserDto;
import com.example.online_library.dto.ViewUserDto;
import com.example.online_library.exception.RecordNotFoundException;
import com.example.online_library.model.User;
import com.example.online_library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplIntTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void findAll() {
        SaveUserDto saveUserDto = SaveUserDto.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        ViewUserDto viewUserDto = userService.save(saveUserDto);
        assertThat(viewUserDto, is(notNullValue()));

        List<User> allUsers = userRepository.findAll();

        assertEquals(1, allUsers.size());
    }

    @Test
    void findById() {
        SaveUserDto saveUserDto = SaveUserDto.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        ViewUserDto viewUserDto = userService.save(saveUserDto);
        assertThat(viewUserDto, is(notNullValue()));

        Optional<User> user = userRepository.findById(viewUserDto.getId());
        assertEquals(viewUserDto.getId(), user.get().getId());
    }

    @Test
    void findByEmail() {
        SaveUserDto saveUserDto = SaveUserDto.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        ViewUserDto viewUserDto = userService.save(saveUserDto);
        assertThat(viewUserDto, is(notNullValue()));

        Optional<User> user = userRepository.findByEmail(viewUserDto.getEmail());
        assertEquals(viewUserDto.getEmail(), user.get().getEmail());
    }

    @Test
    public void saveSuccess() {
        SaveUserDto saveUserDto = SaveUserDto.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        ViewUserDto viewUserDto = userService.save(saveUserDto);
        assertThat(viewUserDto, is(notNullValue()));

        Optional<User> user = userRepository.findById(viewUserDto.getId());
        assertEquals(saveUserDto.getEmail(), user.get().getEmail());
        assertEquals(saveUserDto.getFirstName(), user.get().getFirstName());
        assertEquals(saveUserDto.getLastName(), user.get().getLastName());
        assertEquals(saveUserDto.getPhoneNumber(), user.get().getPhoneNumber());
    }

    @Test
    void updateSuccess() {
        SaveUserDto saveUserDto = SaveUserDto.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        ViewUserDto savedUser = userService.save(saveUserDto);
        assertThat(savedUser, is(notNullValue()));

        savedUser.setEmail("smith@example.com");

        ViewUserDto updatedUser = userService.update(savedUser.getId(), saveUserDto);

        Optional<User> user = userRepository.findById(updatedUser.getId());
        assertEquals(updatedUser.getEmail(), user.get().getEmail());
        assertEquals(updatedUser.getFirstName(), user.get().getFirstName());
        assertEquals(updatedUser.getLastName(), user.get().getLastName());
        assertEquals(updatedUser.getPhoneNumber(), user.get().getPhoneNumber());
    }

    @Test
    void deleteById() {
        SaveUserDto saveUserDto = SaveUserDto.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        ViewUserDto savedUser = userService.save(saveUserDto);
        assertThat(savedUser, is(notNullValue()));

        userService.deleteById(savedUser.getId());

        assertThrows(RecordNotFoundException.class, () -> userService.findById(savedUser.getId()) );
    }
}