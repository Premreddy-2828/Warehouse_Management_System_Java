package com.example.warehouse.service;

import com.example.warehouse.dto.UsersDTO;
import com.example.warehouse.entity.Users;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.mapper.UsersMapper;
import com.example.warehouse.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository)
    {

        this.usersRepository = usersRepository;

    }
    @Autowired
    private UsersMapper usersMapper;
    public UsersDTO createUser(UsersDTO dto) {

        Users users = usersMapper.toEntity(dto);

        Users savedUser = usersRepository.save(users);

        return usersMapper.toDTO(savedUser);
    }
    public List<UsersDTO> getAllUsers() {

        List<Users> users = usersRepository.findAll();

        return users.stream()
                .map(UsersMapper::toDTO)
                .toList();
    }
    public UsersDTO getUserById(Long id) {

        Users users = usersRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        return UsersMapper.toDTO(users);
    }
    public UsersDTO updateUser(Long id, UsersDTO dto) {

        Users users = usersRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        users.setName(dto.getName());
        users.setEmail(dto.getEmail());
        users.setPassword(dto.getPassword());
        users.setRole(dto.getRole());

        Users updatedUser = usersRepository.save(users);

        return UsersMapper.toDTO(updatedUser);
    }
    public void deleteUser(Long id) {

        Users users = usersRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
        usersRepository.delete(users);
    }
}
