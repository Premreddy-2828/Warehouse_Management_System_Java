package com.example.warehouse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StorageDTO {

    private Long id;

    private Long warehouseId;

    private String name;

    private String location;

    private Integer capacity;

    private String status;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
