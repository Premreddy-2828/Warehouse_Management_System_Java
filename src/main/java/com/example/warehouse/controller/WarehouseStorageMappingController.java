package com.example.warehouse.controller;

import com.example.warehouse.mapping.WarehouseStorageMapping;
import com.example.warehouse.service.WarehouseStorageMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse-storage-mappings")
@RequiredArgsConstructor
public class WarehouseStorageMappingController {

    private final WarehouseStorageMappingService mappingService;

    // CREATE
    @PostMapping
    public ResponseEntity<WarehouseStorageMapping> createMapping(
            @RequestBody WarehouseStorageMapping mapping) {

        return ResponseEntity.ok(
                mappingService.createMapping(mapping)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<WarehouseStorageMapping>> getAllMappings() {

        return ResponseEntity.ok(
                mappingService.getAllMappings()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseStorageMapping> getMappingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                mappingService.getMappingById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<WarehouseStorageMapping> updateMapping(
            @PathVariable Long id,
            @RequestBody WarehouseStorageMapping mapping) {

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
