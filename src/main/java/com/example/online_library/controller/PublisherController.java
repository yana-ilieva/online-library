package com.example.online_library.controller;

import com.example.online_library.dto.PublisherDto;
import com.example.online_library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<Set<PublisherDto>> findAll(){
        return ResponseEntity.ok(publisherService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PublisherDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(publisherService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<PublisherDto> findByName(@PathVariable String name){
        return ResponseEntity.ok(publisherService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<PublisherDto> save(@RequestBody @Valid PublisherDto publisherDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.save(publisherDto));
    }

    @PutMapping
    public ResponseEntity<PublisherDto> update(@RequestBody @Valid PublisherDto publisherDto){
        return ResponseEntity.ok(publisherService.update(publisherDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        publisherService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
