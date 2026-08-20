package com.example.warehouse.controller;

import com.example.warehouse.dto.StorageDTO;
import com.example.warehouse.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storages")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<StorageDTO> createStorage(
            @RequestBody StorageDTO storageDTO) {

        return ResponseEntity.ok(
                storageService.createStorage(storageDTO)
        );
    }
    @GetMapping
    public List<StorageDTO> getAllStorages()
    {

        return storageService.getAllStorages();
    }
    @GetMapping("/{id}")
    public StorageDTO getStorageById(@PathVariable Long id)
    {

        return storageService.getStorageById(id);
    }
    @PutMapping("/{id}")
    public StorageDTO updateStorage(
            @PathVariable Long id,
            @RequestBody StorageDTO storageDTO)
    {

        return storageService.updateStorage(id, storageDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStorage(@PathVariable Long id)
    {

        storageService.deleteStorage(id);

        return ResponseEntity.ok("Storage deleted successfully");
    }
}
