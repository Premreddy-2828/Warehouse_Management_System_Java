package com.example.warehouse.repository;

import com.example.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WarehouseRepository
        extends JpaRepository<Warehouse, Long>,
        JpaSpecificationExecutor<Warehouse> {
}

