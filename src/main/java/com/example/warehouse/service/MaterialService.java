package com.example.warehouse.service;

import com.example.warehouse.dto.MaterialDTO;
import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.mapper.MaterialMapper;
import com.example.warehouse.repository.MaterialRepository;
import com.example.warehouse.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.warehouse.dto.MaterialSearchDTO;
import com.example.warehouse.specification.MaterialSpecification;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final WarehouseRepository warehouseRepository;

    public MaterialService(MaterialRepository materialRepository,
                           MaterialMapper materialMapper,
                           WarehouseRepository warehouseRepository) {

        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
        this.warehouseRepository = warehouseRepository;
    }

    // Create Material
    public MaterialDTO createMaterial(MaterialDTO materialDTO) {

        Material material = materialMapper.toEntity(materialDTO);

        Warehouse warehouse = warehouseRepository.findById(materialDTO.getWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        material.setWarehouseId(warehouse.getId());

        Material savedMaterial = materialRepository.save(material);

        return materialMapper.toDTO(savedMaterial);
    }

    // Get All Materials
    public Page<MaterialDTO> getAllMaterials(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return materialRepository.findAll(pageable)
                .map(materialMapper::toDTO);
    }
    // Search Materials with Pagination
    public Page<MaterialDTO> searchMaterials(
            MaterialSearchDTO searchDTO,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return materialRepository
                .findAll(MaterialSpecification.search(searchDTO), pageable)
                .map(materialMapper::toDTO);
    }

    // Get Material By Id
    public MaterialDTO getMaterialById(Long id) {

        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found"));

        return materialMapper.toDTO(material);
    }

    // Update Material
    public MaterialDTO updateMaterial(Long id, MaterialDTO materialDTO) {

        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found"));

        Warehouse warehouse = warehouseRepository.findById(materialDTO.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        material.setName(materialDTO.getName());
        material.setSku(materialDTO.getSku());
        material.setCategory(materialDTO.getCategory());
        material.setQuantity(materialDTO.getQuantity());
        material.setUnit(materialDTO.getUnit());
        material.setPrice(materialDTO.getPrice());
        material.setWarehouseId(warehouse.getId());
        material.setStatus(materialDTO.getStatus());
        material.setUpdatedAt(materialDTO.getUpdatedAt());
        material.setUpdatedBy(materialDTO.getUpdatedBy());

        Material updatedMaterial = materialRepository.save(material);

        return materialMapper.toDTO(updatedMaterial);
    }

    // Delete Material
    public void deleteMaterial(Long id) {

        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found"));

        materialRepository.delete(material);
    }
}
