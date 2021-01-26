package com.example.online_library.service;

import com.example.online_library.dto.RoleDto;
import com.example.online_library.dto.UpdateRoleDto;
import com.example.online_library.exception.DuplicateRecordException;
import com.example.online_library.exception.RecordNotFoundException;
import com.example.online_library.model.Role;
import com.example.online_library.repository.RoleRepository;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<RoleDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleDto findById(Long id) {
        return roleRepository.findById(id)
                .map(role -> modelMapper.map(role, RoleDto.class))
                .orElseThrow(() -> new RecordNotFoundException("Role with id " + id + " not found."));
    }

    @Override
    public RoleDto findByName(String name) {
        return roleRepository.findByName(name)
                .map(role -> modelMapper.map(role, RoleDto.class))
                .orElseThrow(() -> new RecordNotFoundException("Role with name " + name + " not found."));
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        try{
            roleDto.setId(null);
            Role role = modelMapper.map(roleDto, Role.class);
            Role savedRole = roleRepository.save(role);
            return modelMapper.map(savedRole, RoleDto.class);
        } catch (DataIntegrityViolationException e){
            throw new DuplicateRecordException("Role with name " + roleDto.getName() + " already exists.");
        }
    }

    @Override
    public RoleDto update(UpdateRoleDto updateRoleDto) {
        try{
            Optional<Role> maybeRole = roleRepository.findById(updateRoleDto.getId());
            maybeRole.ifPresent(role -> modelMapper.map(updateRoleDto, role));
            Role updatedRole = roleRepository.save(maybeRole.get());
            return modelMapper.map(updatedRole, RoleDto.class);
        }
        catch (NoSuchElementException e){
            throw new RecordNotFoundException("Author with id " + updateRoleDto.getId() + " does not exist.");
        }
        catch (DataIntegrityViolationException e){
            throw new DuplicateRecordException("Role with name " + updateRoleDto.getName() + " already exists.");
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            roleRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new RecordNotFoundException("Role with id " + id + " not found.");
        }
    }
}
