package com.example.online_library.service;

import com.example.online_library.dto.GenreDto;
import com.example.online_library.exception.DuplicateRecordException;
import com.example.online_library.exception.RecordNotFoundException;
import com.example.online_library.model.Genre;
import com.example.online_library.repository.GenreRepository;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, ModelMapper modelMapper) {
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<GenreDto> findAll(){
        return genreRepository.findAll()
                .stream()
                .map(genre -> modelMapper.map(genre, GenreDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public GenreDto findById(@NonNull Long id){
        return genreRepository.findById(id)
                .map(genre -> modelMapper.map(genre, GenreDto.class))
                .orElseThrow(() -> new RecordNotFoundException("Genre with id " + id + " not found."));
    }

    @Override
    public GenreDto findByName(@NonNull String name){
        return genreRepository.findByName(name)
                .map(genre -> modelMapper.map(genre, GenreDto.class))
                .orElseThrow(() -> new RecordNotFoundException("Genre with name " + name + " not found."));
    }

    @Override
    public GenreDto save(@NonNull GenreDto genreDto) {
        try{
            genreDto.setId(null);
            Genre genre = modelMapper.map(genreDto, Genre.class);
            Genre savedGenre = genreRepository.save(genre);
            return modelMapper.map(savedGenre, GenreDto.class);
        } catch (DataIntegrityViolationException e){
            throw new DuplicateRecordException("Genre with name " + genreDto.getName() + " already exists.");
        }
    }

    @Override
    public GenreDto update(@NonNull GenreDto genreDto) {
        try{
            Optional<Genre> maybeGenre = genreRepository.findById(genreDto.getId());
            if(maybeGenre.isPresent()){
                modelMapper.map(genreDto, maybeGenre.get());
                Genre updatedGenre = genreRepository.save(maybeGenre.get());
                return modelMapper.map(updatedGenre, GenreDto.class);
            } else{
                throw new NoSuchElementException();
            }
        }
        catch (IllegalArgumentException e){
            throw new RecordNotFoundException("Genre id must not be null.");
        }
        catch (NoSuchElementException e){
            throw new RecordNotFoundException("Genre with id " + genreDto.getId() + " does not exist.");
        }
        catch (DataIntegrityViolationException e){
            throw new DuplicateRecordException("Genre with name " + genreDto.getName() + " already exists.");
        }
    }

    @Override
    public void deleteById(@NonNull Long id) {
        try{
            genreRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new RecordNotFoundException("Genre with id " + id + " not found.");
        }
    }
}
