package com.example.online_library.config;

import com.example.online_library.dto.ViewBookDto;
import com.example.online_library.model.Book;
import com.example.online_library.model.Genre;
import com.example.online_library.model.Publisher;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

       /* Converter<ViewBookDto, Book> viewBookDtoBookConverter = context -> {
            context.getDestination().setTitle(context.getSource().getTitle());
            context.getDestination().setIsbn(context.getSource().getIsbn());
            context.getDestination().setInventoryNumber(context.getSource().getInventoryNumber());
            context.getDestination().setIssueDate(context.getSource().getIssueDate());
            context.getDestination().setGenre(Genre.builder().name(context.getSource().getGenre()).build());
            context.getDestination().setPublisher(Publisher.builder().name(context.getSource().getPublisher()).build());

            return null;
        };*/

        return modelMapper;
    }
}
