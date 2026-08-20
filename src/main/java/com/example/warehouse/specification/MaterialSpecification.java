package com.example.warehouse.specification;

import com.example.warehouse.dto.MaterialSearchDTO;
import com.example.warehouse.entity.Material;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MaterialSpecification {

    public static Specification<Material> search(MaterialSearchDTO searchDTO) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Name
            if (searchDTO.getName() != null &&
                    !searchDTO.getName().isBlank()) {

                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + searchDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            // SKU
            if (searchDTO.getSku() != null &&
                    !searchDTO.getSku().isBlank()) {

                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("sku")),
                                "%" + searchDTO.getSku().toLowerCase() + "%"
                        )
                );
            }

            // Category
            if (searchDTO.getCategory() != null &&
                    !searchDTO.getCategory().isBlank()) {

                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("category")),
                                "%" + searchDTO.getCategory().toLowerCase() + "%"
                        )
                );
            }

            // Unit
            if (searchDTO.getUnit() != null &&
                    !searchDTO.getUnit().isBlank()) {

                predicates.add(
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(root.get("unit")),
                                searchDTO.getUnit().toLowerCase()
                        )
                );
            }

            // Warehouse ID
            if (searchDTO.getWarehouseId() != null) {

                predicates.add(
                        criteriaBuilder.equal(
                                root.get("warehouse").get("id"),
                                searchDTO.getWarehouseId()
                        )
                );
            }

            // Status
            if (searchDTO.getStatus() != null &&
                    !searchDTO.getStatus().isBlank()) {

                predicates.add(
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(root.get("status")),
                                searchDTO.getStatus().toLowerCase()
                        )
                );
            }

            return criteriaBuilder.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}
