package com.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserAssignedStorageDTO {

    private Long storageId;
    private List<UserAssignedMaterialDTO> materials;
}
