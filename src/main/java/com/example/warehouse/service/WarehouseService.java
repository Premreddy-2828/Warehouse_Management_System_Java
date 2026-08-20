package com.example.warehouse.service;

import com.example.warehouse.dto.WarehouseDTO;
import com.example.warehouse.dto.WarehouseSearchDTO;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.mapper.WarehouseMapper;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.specification.WarehouseSpecification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public WarehouseService(
            WarehouseRepository warehouseRepository,
            WarehouseMapper warehouseMapper) {

        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    // CREATE
    public WarehouseDTO createWarehouse(WarehouseDTO dto) {

        Warehouse warehouse = warehouseMapper.toEntity(dto);

        warehouse.setCreatedAt(LocalDateTime.now());

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        return warehouseMapper.toDTO(savedWarehouse);
    }

    // GET BY ID
    public WarehouseDTO getWarehouseById(Long id) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id: " + id));

        return warehouseMapper.toDTO(warehouse);
    }

    // GET ALL
    public Page<WarehouseDTO> getAllWarehouses(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return warehouseRepository.findAll(pageable)
                .map(warehouseMapper::toDTO);
    }

    // SEARCH + PAGINATION + SORTING
    public Page<WarehouseDTO> searchWarehouses(
            WarehouseSearchDTO searchDTO,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return warehouseRepository
                .findAll(WarehouseSpecification.search(searchDTO), pageable)
                .map(warehouseMapper::toDTO);
    }

    // UPDATE
    public WarehouseDTO updateWarehouse(
            Long id,
            WarehouseDTO dto) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id: " + id));

        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        warehouse.setCapacity(dto.getCapacity());
        warehouse.setStatus(dto.getStatus());

        // User ID mapping
        warehouse.setUserId(dto.getUserId());

        warehouse.setUpdatedAt(LocalDateTime.now());
        warehouse.setUpdatedBy(dto.getUpdatedBy());

        Warehouse updatedWarehouse =
                warehouseRepository.save(warehouse);

        return warehouseMapper.toDTO(updatedWarehouse);
    }

    // DELETE
    public void deleteWarehouse(Long id) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id: " + id));

        warehouseRepository.delete(warehouse);
    }
}