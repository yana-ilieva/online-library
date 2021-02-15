package com.example.online_library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SaveBookDto {

    @NotNull
    private Long inventoryNumber;

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @NotNull
    private Date issueDate;

    private Set<AuthorDto> authors;

    private GenreDto genre;

    private PublisherDto publisher;
}
