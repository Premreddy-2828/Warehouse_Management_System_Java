package com.example.warehouse.mapper;

import com.example.warehouse.dto.WarehouseDTO;
import com.example.warehouse.entity.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    WarehouseDTO toDTO(Warehouse warehouse);

    Warehouse toEntity(WarehouseDTO dto);
}
