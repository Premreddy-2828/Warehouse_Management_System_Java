package com.example.warehouse.controller;


import com.example.warehouse.entity.Users;
import com.example.warehouse.mapping.UserWarehouseMapping;
import com.example.warehouse.service.UserWarehouseMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-warehouse-mappings")
@RequiredArgsConstructor
public class UserWarehouseMappingController {

    private final UserWarehouseMappingService mappingService;

    // CREATE
    @PostMapping
    public ResponseEntity<UserWarehouseMapping> createMapping(
            @RequestBody UserWarehouseMapping mapping) {

        return ResponseEntity.ok(
                mappingService.createMapping(mapping)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<UserWarehouseMapping>> getAllMappings() {

        return ResponseEntity.ok(
                mappingService.getAllMappings()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserWarehouseMapping> getMappingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                mappingService.getMappingById(id)
        );
    }

    // GET MAPPINGS BY WAREHOUSE
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<UserWarehouseMapping>> getMappingsByWarehouse(
            @PathVariable Long warehouseId) {

        return ResponseEntity.ok(
                mappingService.getMappingsByWarehouse(warehouseId)
        );
    }

    // GET USERS BY WAREHOUSE
    @GetMapping("/warehouse/{warehouseId}/users")
    public ResponseEntity<List<Users>> getUsersByWarehouse(
            @PathVariable Long warehouseId) {

        return ResponseEntity.ok(
                mappingService.getUsersByWarehouse(warehouseId)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserWarehouseMapping> updateMapping(
            @PathVariable Long id,
            @RequestBody UserWarehouseMapping mapping) {

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