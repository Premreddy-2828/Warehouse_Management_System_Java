package com.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StorageCapacityDTO {

    private Long storageId;
    private Integer storageCapacity;
    private Integer usedCapacity;
    private Integer availableCapacity;
}
