package com.example.online_library.service;

import com.example.online_library.dto.RoleDto;
import com.example.online_library.dto.SaveUserDto;
import com.example.online_library.dto.ViewUserDto;
import com.example.online_library.exception.DuplicateRecordException;
import com.example.online_library.model.User;
import com.example.online_library.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Mock
    private RoleService roleService;

    @BeforeEach
    public void setUp(){
        userService = new UserServiceImpl(userRepository, roleService, new ModelMapper());
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
        User user = User.builder().id(1L).build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        ViewUserDto viewUserDto = userService.findById(user.getId());

        Long expected = user.getId();
        Long actual = viewUserDto.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void findByEmail() {
        User user = User.builder().id(1L).email("test@test.com").build();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ViewUserDto viewUserDto = userService.findByEmail(user.getEmail());

        String expected = user.getEmail();
        String actual = viewUserDto.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    public void saveExpectNPE() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> userService.save(null));

        String expectedMessage = "saveUserDto is marked non-null but is null";
        String actualMessage = nullPointerException.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void saveExpectDuplicateRecordException() {
        User user = User.builder()
                .firstName("test")
                .lastName("test")
                .email("test@test.com")
                .phoneNumber("123456789")
                .password("12345")
                .registrationDate(new Date())
                .build();

        SaveUserDto saveUserDto = SaveUserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();

        when(userRepository.save(any(User.class))).thenThrow(DataIntegrityViolationException.class);
        when(roleService.findByName("ROLE_READER")).thenReturn(RoleDto.builder().build());

        assertThrows(DuplicateRecordException.class, () -> userService.save(saveUserDto));
    }

    @Test
    public void saveSuccess() {

    }

    @Test
    public void updateEmailSuccess()  {

    }

    @Test
    public void deleteByIdExpectNPE() {
        assertThrows(NullPointerException.class, () -> userService.deleteById(null));
    }

    @Test
    public void deleteByIdSuccess() {
        Long id = 1L;
        userService.deleteById(id);
        verify(userRepository, times(1)).deleteById(id);
    }
}