package com.example.online_library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateAuthorDto {

    @NotNull
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
