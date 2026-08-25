package com.example.warehouse.controller;

import com.example.warehouse.entity.Material;
import com.example.warehouse.mapping.StorageMaterialMapping;
import com.example.warehouse.service.StorageMaterialMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storage-material-mappings")
@RequiredArgsConstructor
public class StorageMaterialMappingController {

    private final StorageMaterialMappingService mappingService;
    private final StorageMaterialMappingService storageMaterialMappingService;

    @GetMapping("/storage/{storageId}/available-capacity")
    public ResponseEntity<?> getAvailableCapacity(@PathVariable Long storageId) {
        return ResponseEntity.ok(
                storageMaterialMappingService.getAvailableCapacity(storageId)
        );
    }

    // CREATE
    @PostMapping
    public ResponseEntity<StorageMaterialMapping> createMapping(
            @RequestBody StorageMaterialMapping mapping) {

        return ResponseEntity.ok(
                mappingService.createMapping(mapping)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<StorageMaterialMapping>> getAllMappings() {

        return ResponseEntity.ok(
                mappingService.getAllMappings()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StorageMaterialMapping> getMappingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                mappingService.getMappingById(id)
        );
    }
    @GetMapping("/storage/{storageId}/materials")
    public ResponseEntity<List<Material>> getMaterialsByStorage(
            @PathVariable Long storageId) {

        return ResponseEntity.ok(
                storageMaterialMappingService.getMaterialsByStorage(storageId)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StorageMaterialMapping> updateMapping(
            @PathVariable Long id,
            @RequestBody StorageMaterialMapping mapping) {

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
