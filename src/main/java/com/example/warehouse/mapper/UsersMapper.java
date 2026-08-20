package com.example.warehouse.mapper;

import com.example.warehouse.dto.UsersDTO;
import com.example.warehouse.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public static UsersDTO toDTO(Users users) {

        UsersDTO dto = new UsersDTO();

        dto.setId(users.getId());
        dto.setName(users.getName());
        dto.setEmail(users.getEmail());
        dto.setPassword(users.getPassword());
        dto.setRole(users.getRole());

        return dto;
    }

    public static Users toEntity(UsersDTO dto) {

        Users users = new Users();

        users.setId(dto.getId());
        users.setName(dto.getName());
        users.setEmail(dto.getEmail());
        users.setPassword(dto.getPassword());
        users.setRole(dto.getRole());

        return users;
    }
}
