package com.example.online_library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ViewBookDto {

    private Long id;

    private Long inventoryNumber;

    private String title;

    private String isbn;

    private Date issueDate;

    private Set<AuthorDto> authors;

    private String genre;

    private String publisher;
}
