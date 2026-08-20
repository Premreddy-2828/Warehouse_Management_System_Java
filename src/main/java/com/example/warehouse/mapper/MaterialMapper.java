package com.example.warehouse.mapper;

import com.example.warehouse.dto.MaterialDTO;
import com.example.warehouse.entity.Material;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialDTO toDTO(Material material);

    Material toEntity(MaterialDTO materialDTO);
}
