package com.example.warehouse.controller;

import com.example.warehouse.dto.WarehouseDTO;
import com.example.warehouse.dto.WarehouseSearchDTO;
import com.example.warehouse.service.WarehouseService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<WarehouseDTO> createWarehouse(
            @Valid @RequestBody WarehouseDTO dto) {

        return ResponseEntity.ok(
                warehouseService.createWarehouse(dto)
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDTO> getWarehouseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                warehouseService.getWarehouseById(id)
        );
    }

    // GET ALL + PAGINATION + SORTING
    @GetMapping
    public ResponseEntity<Page<WarehouseDTO>> getAllWarehouses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                warehouseService.getAllWarehouses(
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }

    // SEARCH + PAGINATION + SORTING
    @GetMapping("/search")
    public ResponseEntity<Page<WarehouseDTO>> searchWarehouses(
            @ModelAttribute WarehouseSearchDTO searchDTO,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                warehouseService.searchWarehouses(
                        searchDTO,
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDTO> updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseDTO dto) {

        return ResponseEntity.ok(
                warehouseService.updateWarehouse(id, dto)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(
            @PathVariable Long id) {

        warehouseService.deleteWarehouse(id);

        return ResponseEntity.noContent().build();
    }
}