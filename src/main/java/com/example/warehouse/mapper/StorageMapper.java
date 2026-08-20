package com.example.warehouse.mapper;

import com.example.warehouse.dto.StorageDTO;
import com.example.warehouse.entity.Storage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorageMapper {

    StorageDTO toDTO(Storage storage);

    Storage toEntity(StorageDTO storageDTO);
}
