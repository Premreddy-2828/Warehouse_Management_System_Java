package com.example.warehouse.repository;

import com.example.warehouse.mapping.StorageMaterialMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageMaterialMappingRepository
        extends JpaRepository<StorageMaterialMapping, Long> {
}
