package com.example.warehouse.service;

import com.example.warehouse.mapping.UserWarehouseMapping;
import com.example.warehouse.repository.UserWarehouseMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWarehouseMappingService {

    private final UserWarehouseMappingRepository mappingRepository;

    // CREATE MAPPING
    public UserWarehouseMapping createMapping(
            UserWarehouseMapping mapping) {

        return mappingRepository.save(mapping);
    }

    // GET ALL MAPPINGS
    public List<UserWarehouseMapping> getAllMappings() {

        return mappingRepository.findAll();
    }

    // GET MAPPING BY ID
    public UserWarehouseMapping getMappingById(Long id) {

        return mappingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User-Warehouse mapping not found with id: " + id
                        ));
    }

    // UPDATE MAPPING
    public UserWarehouseMapping updateMapping(
            Long id,
            UserWarehouseMapping mapping) {

        UserWarehouseMapping existingMapping =
                mappingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User-Warehouse mapping not found with id: " + id
                                ));

        existingMapping.setUserId(mapping.getUserId());
        existingMapping.setWarehouseId(mapping.getWarehouseId());

        return mappingRepository.save(existingMapping);
    }

    // DELETE MAPPING
    public void deleteMapping(Long id) {

        UserWarehouseMapping mapping =
                mappingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User-Warehouse mapping not found with id: " + id
                                ));

        mappingRepository.delete(mapping);
    }
}
