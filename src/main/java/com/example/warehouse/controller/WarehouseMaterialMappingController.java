package com.example.warehouse.controller;

import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.mapping.WarehouseMaterialMapping;
import com.example.warehouse.service.WarehouseMaterialMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse-material-mappings")
@RequiredArgsConstructor
public class WarehouseMaterialMappingController {

    private final WarehouseMaterialMappingService mappingService;

    // CREATE
    @PostMapping
    public ResponseEntity<WarehouseMaterialMapping> createMapping(
            @RequestBody WarehouseMaterialMapping mapping) {

        return ResponseEntity.ok(
                mappingService.createMapping(mapping)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<WarehouseMaterialMapping>> getAllMappings() {

        return ResponseEntity.ok(
                mappingService.getAllMappings()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseMaterialMapping> getMappingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                mappingService.getMappingById(id)
        );
    }
    // GET MAPPINGS BY WAREHOUSE
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<WarehouseMaterialMapping>> getMappingsByWarehouse(
            @PathVariable Long warehouseId) {

        return ResponseEntity.ok(
                mappingService.getMappingsByWarehouse(warehouseId)
        );
    }
    @GetMapping("/warehouse/{warehouseId}/materials")
    public ResponseEntity<List<Material>> getMaterialsByWarehouse(
            @PathVariable Long warehouseId) {

        return ResponseEntity.ok(
                mappingService.getMaterialsByWarehouse(warehouseId)
        );
    }
    @GetMapping("/material/{materialId}/warehouses")
    public ResponseEntity<List<Warehouse>> getWarehousesByMaterial(
            @PathVariable Long materialId) {

        return ResponseEntity.ok(
                mappingService.getWarehousesByMaterial(materialId)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<WarehouseMaterialMapping> updateMapping(
            @PathVariable Long id,
            @RequestBody WarehouseMaterialMapping mapping) {

        return ResponseEntity.ok(
                mappingService.updateMapping(id, mapping)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapping(
            @PathVariable Long id) {

        mappingService.deleteMapping(id);

        return ResponseEntity.noContent().build();
    }
}
