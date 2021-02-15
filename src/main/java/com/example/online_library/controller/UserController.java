package com.example.online_library.controller;

import com.example.online_library.dto.SaveUserDto;
import com.example.online_library.dto.ViewUserDto;
import com.example.online_library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ViewUserDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<ViewUserDto> findByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping
    public ResponseEntity<Set<ViewUserDto>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<ViewUserDto> save(@RequestBody @Valid SaveUserDto saveUserDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(saveUserDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ViewUserDto> update(@PathVariable Long id, @RequestBody @Valid SaveUserDto saveUserDto){
        return ResponseEntity.ok(userService.update(id, saveUserDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
