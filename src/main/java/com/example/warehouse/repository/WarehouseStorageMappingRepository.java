package com.example.warehouse.repository;

import com.example.warehouse.mapping.WarehouseStorageMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseStorageMappingRepository
        extends JpaRepository<WarehouseStorageMapping, Long> {

    List<WarehouseStorageMapping> findByWarehouseId(Long warehouseId);
}
