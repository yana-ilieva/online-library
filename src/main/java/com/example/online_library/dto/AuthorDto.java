package com.example.online_library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthorDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
