package com.example.warehouse.repository;

import com.example.warehouse.mapping.UserWarehouseMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWarehouseMappingRepository
        extends JpaRepository<UserWarehouseMapping, Long> {
    List<UserWarehouseMapping> findByWarehouseId(Long warehouseId);
}
