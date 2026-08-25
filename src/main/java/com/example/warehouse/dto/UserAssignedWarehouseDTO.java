package com.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserAssignedWarehouseDTO {

    private Long warehouseId;
    private List<UserAssignedStorageDTO> storages;
}
