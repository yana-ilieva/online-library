package com.example.online_library.service;

import com.example.online_library.dto.RoleDto;
import com.example.online_library.dto.UpdateRoleDto;

import java.util.Set;

public interface RoleService {
    Set<RoleDto> findAll();
    RoleDto findById(Long id);
    RoleDto findByName(String name);
    RoleDto save(RoleDto roleDto);
    RoleDto update(UpdateRoleDto updateRoleDto);
    void deleteById(Long id);
}
