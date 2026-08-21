package com.example.warehouse.service;

import com.example.warehouse.entity.Material;
import com.example.warehouse.mapping.WarehouseMaterialMapping;
import com.example.warehouse.repository.MaterialRepository;
import com.example.warehouse.repository.WarehouseMaterialMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseMaterialMappingService {

    private final WarehouseMaterialMappingRepository mappingRepository;
    private final MaterialRepository materialRepository;

    // CREATE MAPPING
    public WarehouseMaterialMapping createMapping(
            WarehouseMaterialMapping mapping) {

        return mappingRepository.save(mapping);
    }

    // GET ALL MAPPINGS
    public List<WarehouseMaterialMapping> getAllMappings() {

        return mappingRepository.findAll();
    }

    // GET MAPPING BY ID
    public WarehouseMaterialMapping getMappingById(Long id) {

        return mappingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Warehouse-Material mapping not found with id: " + id
                        ));
    }

    // GET MAPPINGS BY WAREHOUSE
    public List<WarehouseMaterialMapping> getMappingsByWarehouse(
            Long warehouseId) {

        return mappingRepository.findByWarehouseId(warehouseId);
    }

    // GET MATERIAL DETAILS BY WAREHOUSE
    public List<Material> getMaterialsByWarehouse(Long warehouseId) {

        List<WarehouseMaterialMapping> mappings =
                mappingRepository.findByWarehouseId(warehouseId);

        return mappings.stream()
                .map(mapping -> materialRepository.findById(mapping.getMaterialId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Material not found with id: "
                                                + mapping.getMaterialId()
                                )))
                .toList();
    }

    // UPDATE MAPPING
    public WarehouseMaterialMapping updateMapping(
            Long id,
            WarehouseMaterialMapping mapping) {

        WarehouseMaterialMapping existingMapping =
                mappingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Warehouse-Material mapping not found with id: " + id
                                ));

        existingMapping.setWarehouseId(mapping.getWarehouseId());
        existingMapping.setMaterialId(mapping.getMaterialId());

        return mappingRepository.save(existingMapping);
    }

    // DELETE MAPPING
    public void deleteMapping(Long id) {

        WarehouseMaterialMapping mapping =
                mappingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Warehouse-Material mapping not found with id: " + id
                                ));

        mappingRepository.delete(mapping);
    }
}