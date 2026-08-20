package com.example.warehouse.service;

import com.example.warehouse.mapping.StorageMaterialMapping;
import com.example.warehouse.repository.StorageMaterialMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageMaterialMappingService {

    private final StorageMaterialMappingRepository mappingRepository;

    // CREATE
    public StorageMaterialMapping createMapping(
            StorageMaterialMapping mapping) {

        return mappingRepository.save(mapping);
    }

    // GET ALL
    public List<StorageMaterialMapping> getAllMappings() {

        return mappingRepository.findAll();
    }


    // GET BY ID
    public StorageMaterialMapping getMappingById(Long id) {

        return mappingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Storage-Material mapping not found with id: " + id
                        ));
    }

    // UPDATE
    public StorageMaterialMapping updateMapping(
            Long id,
            StorageMaterialMapping mapping) {

        StorageMaterialMapping existingMapping =
                mappingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Storage-Material mapping not found with id: " + id
                                ));

        existingMapping.setStorageId(mapping.getStorageId());
        existingMapping.setMaterialId(mapping.getMaterialId());

        return mappingRepository.save(existingMapping);
    }

    // DELETE
    public void deleteMapping(Long id) {

        StorageMaterialMapping mapping =
                mappingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Storage-Material mapping not found with id: " + id
                                ));

        mappingRepository.delete(mapping);
    }
}
