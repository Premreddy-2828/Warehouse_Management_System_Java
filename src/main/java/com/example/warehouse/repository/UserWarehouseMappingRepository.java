package com.example.warehouse.repository;

import com.example.warehouse.mapping.UserWarehouseMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWarehouseMappingRepository
        extends JpaRepository<UserWarehouseMapping, Long> {
}
