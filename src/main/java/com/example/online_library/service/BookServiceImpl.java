package com.example.online_library.service;

import com.example.online_library.dto.SaveUserDto;
import com.example.online_library.dto.UpdateBookDto;
import com.example.online_library.dto.ViewBookDto;
import com.example.online_library.exception.RecordNotFoundException;
import com.example.online_library.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<ViewBookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, ViewBookDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public ViewBookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(book -> modelMapper.map(book, ViewBookDto.class))
                .orElseThrow(() -> new RecordNotFoundException("Book with id: " + id + " not found."));
    }

    @Override
    public Set<ViewBookDto> findByTitle(String title) {
        Set<ViewBookDto> books = bookRepository.findByTitle(title)
                .stream()
                .map(book -> modelMapper.map(book, ViewBookDto.class))
                .collect(Collectors.toSet());
        if(!books.isEmpty()){
            return books;
        }
        throw new RecordNotFoundException("Book with title: " + title + " not found");
    }

    @Override
    public ViewBookDto findByInventoryNumber(Long number) {
        return bookRepository.findByInventoryNumber(number)
                .map(book -> modelMapper.map(book, ViewBookDto.class))
                .orElseThrow(() -> new RecordNotFoundException("Book with inventory number: " + number + " not found."));
    }

    @Override
    public Set<ViewBookDto> findByIsbn(Long isbn) {
        Set<ViewBookDto> books = bookRepository.findByIsbn(isbn)
                .stream()
                .map(book -> modelMapper.map(book, ViewBookDto.class))
                .collect(Collectors.toSet());
        if(!books.isEmpty()){
            return books;
        }
        throw new RecordNotFoundException("Book with isbn: " + isbn + " not found");
    }

    @Override
    public Set<ViewBookDto> findByAuthor(String name) {
        Set<ViewBookDto> books = bookRepository.findByAuthors(name)
                .stream()
                .map(book -> modelMapper.map(book, ViewBookDto.class))
                .collect(Collectors.toSet());
        if(!books.isEmpty()){
            return books;
        }
        throw new RecordNotFoundException("Book with name: " + name + " not found");
    }

    @Override
    public Set<ViewBookDto> findByGenre(String genre) {
        Set<ViewBookDto> books = bookRepository.findByGenre(genre)
                .stream()
                .map(book -> modelMapper.map(book, ViewBookDto.class))
                .collect(Collectors.toSet());
        if(!books.isEmpty()){
            return books;
        }
        throw new RecordNotFoundException("Book with genre: " + genre + " not found");
    }

    @Override
    public Set<ViewBookDto> findByPublisher(String publisher) {
        Set<ViewBookDto> books = bookRepository.findByPublisher(publisher)
                .stream()
                .map(book -> modelMapper.map(book, ViewBookDto.class))
                .collect(Collectors.toSet());
        if(!books.isEmpty()){
            return books;
        }
        throw new RecordNotFoundException("Book with publisher: " + publisher + " not found");
    }

    @Override
    public ViewBookDto save(SaveUserDto saveUserDto) {
        return null;
    }

    @Override
    public ViewBookDto update(UpdateBookDto updateBookDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        try{
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new RecordNotFoundException("Book with id: " + id + " not found");
        }
    }
}
