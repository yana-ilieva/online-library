package com.example.online_library.service;

import com.example.online_library.dto.PublisherDto;
import com.example.online_library.exception.DuplicateRecordException;
import com.example.online_library.exception.RecordNotFoundException;
import com.example.online_library.model.Publisher;
import com.example.online_library.repository.PublisherRepository;
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
public class PublisherServiceImpl implements PublisherService{

    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, ModelMapper modelMapper) {
        this.publisherRepository = publisherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PublisherDto findById(@NonNull Long id) {
        return publisherRepository.findById(id)
                .map(publisher -> modelMapper.map(publisher, PublisherDto.class))
                .orElseThrow(() -> new RecordNotFoundException("Publisher with id " + id + " not found."));
    }

    @Override
    public PublisherDto findByName(@NonNull String name) {
        return publisherRepository.findByName(name)
                .map(publisher -> modelMapper.map(publisher, PublisherDto.class))
                .orElseThrow(() -> new RecordNotFoundException("Publisher with name " + name + " not found."));
    }

    @Override
    public Set<PublisherDto> findAll() {
        return publisherRepository.findAll()
                .stream()
                .map(publisher -> modelMapper.map(publisher, PublisherDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public PublisherDto save(@NonNull PublisherDto publisherDto) {
        try{
            publisherDto.setId(null);
            Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
            Publisher savedPublisher = publisherRepository.save(publisher);
            return modelMapper.map(savedPublisher, PublisherDto.class);
        } catch (DataIntegrityViolationException e){
            throw new DuplicateRecordException("Publisher with name " + publisherDto.getName() + " already exists.");
        }
    }

    @Override
    public PublisherDto update(@NonNull PublisherDto publisherDto) {
        try{
            Optional<Publisher> maybePublisher = publisherRepository.findById(publisherDto.getId());
            if(maybePublisher.isPresent()){
                modelMapper.map(publisherDto, maybePublisher.get());
                Publisher updatedPublisher = publisherRepository.save(maybePublisher.get());
                return modelMapper.map(updatedPublisher, PublisherDto.class);
            } else{
                throw new NoSuchElementException();
            }
        }
        catch (IllegalArgumentException e){
            throw new RecordNotFoundException("Publisher id must not be null.");
        }
        catch (NoSuchElementException e){
            throw new RecordNotFoundException("Publisher with id " + publisherDto.getId() + " does not exist.");
        }
        catch (DataIntegrityViolationException e){
            throw new DuplicateRecordException("Publisher with name " + publisherDto.getName() + " already exists.");
        }
    }

    @Override
    public void deleteById(@NonNull Long id) {
        try{
            publisherRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new RecordNotFoundException("Publisher with id " + id + " not found.");
        }
    }
}
