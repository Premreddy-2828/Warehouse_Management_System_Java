package com.example.warehouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialSearchDTO {

    private String name;

    private String sku;

    private String category;

    private String unit;

    private Long warehouseId;

    private String status;
}
