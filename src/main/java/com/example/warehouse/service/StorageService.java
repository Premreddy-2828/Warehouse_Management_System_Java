package com.example.warehouse.service;

import com.example.warehouse.dto.StorageDTO;
import com.example.warehouse.entity.Storage;
import com.example.warehouse.mapper.StorageMapper;
import com.example.warehouse.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final StorageMapper storageMapper;

    public StorageDTO createStorage(StorageDTO storageDTO) {

        Storage storage = storageMapper.toEntity(storageDTO);

        storage.setCreatedAt(LocalDateTime.now());

        Storage savedStorage = storageRepository.save(storage);

        return storageMapper.toDTO(savedStorage);
    }

    public List<StorageDTO> getAllStorages() {

        List<Storage> storages = storageRepository.findAll();

        return storages.stream()
                .map(storageMapper::toDTO)
                .toList();
    }

    public StorageDTO getStorageById(Long id) {

        Storage storage = storageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Storage not found with id: " + id));

        return storageMapper.toDTO(storage);
    }

    public StorageDTO updateStorage(Long id, StorageDTO storageDTO) {

        Storage storage = storageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Storage not found with id: " + id));

        storage.setWarehouseId(storageDTO.getWarehouseId());
        storage.setName(storageDTO.getName());
        storage.setLocation(storageDTO.getLocation());
        storage.setCapacity(storageDTO.getCapacity());
        storage.setStatus(storageDTO.getStatus());

        storage.setUpdatedAt(LocalDateTime.now());
        storage.setUpdatedBy(storageDTO.getUpdatedBy());

        Storage updatedStorage = storageRepository.save(storage);

        return storageMapper.toDTO(updatedStorage);
    }

    public void deleteStorage(Long id) {

        Storage storage = storageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Storage not found with id: " + id
                ));

        storageRepository.delete(storage);
    }

}
