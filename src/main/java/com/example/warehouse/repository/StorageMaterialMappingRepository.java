package com.example.warehouse.repository;

import com.example.warehouse.mapping.StorageMaterialMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageMaterialMappingRepository
        extends JpaRepository<StorageMaterialMapping, Long> {

    List<StorageMaterialMapping> findByStorageId(Long storageId);
}