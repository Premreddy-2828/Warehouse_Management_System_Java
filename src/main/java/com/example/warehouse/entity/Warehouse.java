package com.example.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
public class Warehouse extends BaseEntity {

    private String name;

    private String location;

    private Integer capacity;

    private String status;

    private Long userId;
}