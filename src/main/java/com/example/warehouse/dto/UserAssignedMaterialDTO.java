package com.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAssignedMaterialDTO {

    private Long id;
    private String name;
    private String sku;
    private Integer quantity;
}
