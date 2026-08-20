package com.example.warehouse.repository;

import com.example.warehouse.mapping.WarehouseStorageMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseStorageMappingRepository
        extends JpaRepository<WarehouseStorageMapping, Long> {
}
