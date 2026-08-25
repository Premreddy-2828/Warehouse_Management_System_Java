package com.example.warehouse.repository;

import com.example.warehouse.mapping.WarehouseMaterialMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseMaterialMappingRepository
        extends JpaRepository<WarehouseMaterialMapping, Long> {

    List<WarehouseMaterialMapping> findByWarehouseId(Long warehouseId);

    List<WarehouseMaterialMapping> findByMaterialId(Long materialId);
}
