package com.example.online_library.service;

import com.example.online_library.dto.AuthorDto;
import com.example.online_library.dto.UpdateAuthorDto;
import com.example.online_library.exception.RecordNotFoundException;
import com.example.online_library.model.Author;
import com.example.online_library.repository.AuthorRepository;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<AuthorDto> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public AuthorDto findById(@NonNull Long id){
        return authorRepository.findById(id)
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .orElseThrow(() -> new RecordNotFoundException("Author with id " + id + " not found."));
    }

    @Override
    public Set<AuthorDto> findByFirstName(@NonNull String name) {
        Set<Author> authors = authorRepository.findByFirstName(name);
        if(authors.isEmpty())
            throw new RecordNotFoundException("Author with name " + name + " not found");
        else
            return authors
                .stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AuthorDto> findByLastName(@NonNull String name) {
        Set<Author> authors = authorRepository.findByLastName(name);
        if(authors.isEmpty())
            throw new RecordNotFoundException("Author with name " + name + " not found");
        else
            return authors
                    .stream()
                    .map(author -> modelMapper.map(author, AuthorDto.class))
                    .collect(Collectors.toSet());
    }

    @Override
    public AuthorDto save(@NonNull AuthorDto authorDto) {
        try{
            authorDto.setId(null);
            Author author = modelMapper.map(authorDto, Author.class);
            Author savedAuthor = authorRepository.save(author);
            return modelMapper.map(savedAuthor, AuthorDto.class);
        } catch (Exception e){
            throw new IllegalArgumentException("Undefined error"); // better alternative needed
        }
    }

    @Override
    public AuthorDto update(@NonNull UpdateAuthorDto updateAuthorDto) {
        try{
            Optional<Author> maybeAuthor = authorRepository.findById(updateAuthorDto.getId());
            if(maybeAuthor.isPresent()){
                modelMapper.map(updateAuthorDto, maybeAuthor.get());
                Author savedAuthor = authorRepository.save(maybeAuthor.get());
                return modelMapper.map(savedAuthor, AuthorDto.class);
            } else{
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e){
            throw new RecordNotFoundException("Author with id " + updateAuthorDto.getId() + " does not exist.");
        }
    }

    @Override
    public void deleteById(@NonNull Long id) {
        try{
            authorRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new RecordNotFoundException("Author with id " + id + " not found.");
        }
    }
}
