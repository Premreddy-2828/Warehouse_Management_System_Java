package com.example.warehouse.service;

import com.example.warehouse.dto.StorageCapacityDTO;
import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Storage;
import com.example.warehouse.mapping.StorageMaterialMapping;
import com.example.warehouse.repository.MaterialRepository;
import com.example.warehouse.repository.StorageMaterialMappingRepository;
import com.example.warehouse.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageMaterialMappingService {

    private final StorageMaterialMappingRepository mappingRepository;
    private final StorageRepository storageRepository;
    private final MaterialRepository materialRepository;

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
    public StorageCapacityDTO getAvailableCapacity(Long storageId) {

        Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Storage not found with id: " + storageId
                        ));

        List<StorageMaterialMapping> mappings =
                mappingRepository.findByStorageId(storageId);

        int usedCapacity = mappings.stream()
                .map(mapping -> materialRepository
                        .findById(mapping.getMaterialId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Material not found with id: "
                                                + mapping.getMaterialId()
                                )))
                .mapToInt(Material::getQuantity)
                .sum();

        int storageCapacity = storage.getCapacity();

        int availableCapacity = storageCapacity - usedCapacity;

        return new StorageCapacityDTO(
                storageId,
                storageCapacity,
                usedCapacity,
                availableCapacity
        );
    }
    // GET MATERIALS BY STORAGE
    public List<Material> getMaterialsByStorage(Long storageId) {

        List<StorageMaterialMapping> mappings =
                mappingRepository.findByStorageId(storageId);

        return mappings.stream()
                .map(mapping -> materialRepository
                        .findById(mapping.getMaterialId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Material not found with id: "
                                                + mapping.getMaterialId()
                                )))
                .toList();
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
