package com.example.online_library.service;

import com.example.online_library.dto.PublisherDto;

import java.util.Set;

public interface PublisherService {
    PublisherDto findById(Long id);
    PublisherDto findByName(String name);
    Set<PublisherDto> findAll();
    PublisherDto save(PublisherDto publisherDto);
    PublisherDto update(PublisherDto publisherDto);
    void deleteById(Long id);
}
