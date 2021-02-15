package com.example.online_library.controller;

import com.example.online_library.dto.RoleDto;
import com.example.online_library.dto.UpdateRoleDto;
import com.example.online_library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Set<RoleDto>> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<RoleDto> findByName(@PathVariable String name){
        return ResponseEntity.ok(roleService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<RoleDto> save(@RequestBody @Valid RoleDto roleDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(roleDto));
    }

    @PutMapping
    public ResponseEntity<RoleDto> update(@RequestBody @Valid UpdateRoleDto updateRoleDto){
        return ResponseEntity.ok().body(roleService.update(updateRoleDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        roleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
