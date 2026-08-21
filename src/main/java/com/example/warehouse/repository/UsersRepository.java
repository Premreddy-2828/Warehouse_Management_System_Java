package com.example.warehouse.repository;

import com.example.warehouse.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    List<Users> findByIdIn(List<Long> ids);
}


