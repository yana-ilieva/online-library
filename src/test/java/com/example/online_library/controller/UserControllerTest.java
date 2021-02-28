package com.example.online_library.controller;

import com.example.online_library.dto.SaveUserDto;
import com.example.online_library.dto.ViewUserDto;
import com.example.online_library.model.User;
import com.example.online_library.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest extends BaseController{

    @MockBean
    private UserService userService;

    @Test
    public void findById() throws Exception {
        ViewUserDto viewUserDto = ViewUserDto.builder().id(1L).build();
        when(userService.findById(1L)).thenReturn(viewUserDto);

        mvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void findByEmail() throws Exception{
        ViewUserDto viewUserDto = ViewUserDto.builder().email("test@test.com").build();
        when(userService.findByEmail(viewUserDto.getEmail())).thenReturn(viewUserDto);

        mvc.perform(get("/users/email/test@test.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(viewUserDto.getEmail())));
    }

    @Test
    public void findAllWhenEmpty() throws Exception {
        when(userService.findAll()).thenReturn(Collections.EMPTY_SET);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]", true));
    }

    @Test
    public void saveSuccess() throws Exception {
        SaveUserDto request = SaveUserDto.builder()
                .lastName("Ilieva")
                .firstName("Yana")
                .phoneNumber("08887033199")
                .email("yana@gmail.com")
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        ViewUserDto response = ViewUserDto.builder()
                .id(1L)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();

        when(userService.save(request)).thenReturn(response);

        mvc.perform(post("/users")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is(request.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(request.getLastName())))
                .andExpect(jsonPath("$.email", is(request.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(request.getPhoneNumber())));
    }

    @Test
    public void updateSuccess() throws Exception{
        SaveUserDto request = SaveUserDto.builder()
                .firstName("John")
                .lastName("Smith")
                .email("test@test.com")
                .phoneNumber("123456789")
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        ViewUserDto viewUserDto = ViewUserDto.builder()
                .email("test@test.com")
                .build();
        when(userService.update(1L, request)).thenReturn(viewUserDto);

        mvc.perform(put("/users/1")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(request.getEmail())));
    }

    @Test
    public void deleteById() throws Exception{
        mvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }
}