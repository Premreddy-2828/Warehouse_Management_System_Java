package com.example.warehouse.repository;

import com.example.warehouse.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaterialRepository
        extends JpaRepository<Material, Long>,
        JpaSpecificationExecutor<Material> {
}
