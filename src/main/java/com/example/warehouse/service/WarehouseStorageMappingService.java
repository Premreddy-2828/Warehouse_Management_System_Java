package com.example.warehouse.service;

import com.example.warehouse.mapping.WarehouseStorageMapping;
import com.example.warehouse.repository.WarehouseStorageMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseStorageMappingService {

    private final WarehouseStorageMappingRepository mappingRepository;

    // CREATE
    public WarehouseStorageMapping createMapping(
            WarehouseStorageMapping mapping) {

        return mappingRepository.save(mapping);
    }

    // GET ALL
    public List<WarehouseStorageMapping> getAllMappings() {

        return mappingRepository.findAll();
    }

    // GET BY ID
    public WarehouseStorageMapping getMappingById(Long id) {

        return mappingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Warehouse-Storage mapping not found with id: " + id
                        ));
    }

    // UPDATE
    public WarehouseStorageMapping updateMapping(
            Long id,
            WarehouseStorageMapping mapping) {

        WarehouseStorageMapping existingMapping =
                mappingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Warehouse-Storage mapping not found with id: " + id
                                ));

        existingMapping.setWarehouseId(mapping.getWarehouseId());
        existingMapping.setStorageId(mapping.getStorageId());

        return mappingRepository.save(existingMapping);
    }

    // DELETE
    public void deleteMapping(Long id) {

        WarehouseStorageMapping mapping =
                mappingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Warehouse-Storage mapping not found with id: " + id
                                ));

        mappingRepository.delete(mapping);
    }
}
