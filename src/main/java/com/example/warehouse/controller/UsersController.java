package com.example.warehouse.controller;

import com.example.warehouse.dto.UsersDTO;
import com.example.warehouse.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService)
    {

        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UsersDTO> createUser(
            @Valid @RequestBody UsersDTO usersDTO) {

        UsersDTO savedUser = usersService.createUser(usersDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public List<UsersDTO> getAllUsers() {

        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UsersDTO getUserById(@PathVariable Long id)
    {

        return usersService.getUserById(id);
    }
    @PutMapping("/{id}")
    public UsersDTO updateUser(
            @PathVariable Long id,
            @RequestBody UsersDTO dto) {

        return usersService.updateUser(id, dto);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        usersService.deleteUser(id);

        return "User deleted successfully";
    }
}
