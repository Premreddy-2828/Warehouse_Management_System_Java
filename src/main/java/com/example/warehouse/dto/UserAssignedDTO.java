package com.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserAssignedDTO {

    private Long userId;
    private List<UserAssignedWarehouseDTO> warehouses;
}
