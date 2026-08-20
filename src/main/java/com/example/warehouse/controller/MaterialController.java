package com.example.warehouse.controller;

import com.example.warehouse.dto.MaterialDTO;
import com.example.warehouse.dto.MaterialSearchDTO;
import com.example.warehouse.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {

        this.materialService = materialService;
    }

    // Create Material
    @PostMapping
    public ResponseEntity<MaterialDTO> createMaterial(
            @Valid @RequestBody MaterialDTO materialDTO) {

        return ResponseEntity.ok(
                materialService.createMaterial(materialDTO)
        );
    }

    // Get All Materials - Pagination
    @GetMapping
    public ResponseEntity<Page<MaterialDTO>> getAllMaterials(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                materialService.getAllMaterials(page, size)
        );
    }

    // Get Material By Id
    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> getMaterialById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                materialService.getMaterialById(id)
        );
    }

    // Update Material
    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> updateMaterial(
            @PathVariable Long id,
            @Valid @RequestBody MaterialDTO materialDTO) {

        return ResponseEntity.ok(
                materialService.updateMaterial(id, materialDTO)
        );
    }

    // Delete Material
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaterial(
            @PathVariable Long id) {

        materialService.deleteMaterial(id);

        return ResponseEntity.ok("Material deleted successfully");
    }
    // Search Materials with Pagination
    @PostMapping("/search")
    public ResponseEntity<Page<MaterialDTO>> searchMaterials(
            @RequestBody MaterialSearchDTO searchDTO,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                materialService.searchMaterials(searchDTO, page, size)
        );
    }
}