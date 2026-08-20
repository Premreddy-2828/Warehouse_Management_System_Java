package com.example.warehouse.specification;

import com.example.warehouse.dto.WarehouseSearchDTO;
import com.example.warehouse.entity.Warehouse;
import org.springframework.data.jpa.domain.Specification;

public class WarehouseSpecification {

    public static Specification<Warehouse> search(WarehouseSearchDTO searchDTO) {

        return (root, query, criteriaBuilder) -> {

            var predicates = criteriaBuilder.conjunction();

            if (searchDTO.getName() != null &&
                    !searchDTO.getName().isBlank()) {

                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + searchDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (searchDTO.getLocation() != null &&
                    !searchDTO.getLocation().isBlank()) {

                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("location")),
                                "%" + searchDTO.getLocation().toLowerCase() + "%"
                        )
                );
            }

            if (searchDTO.getStatus() != null &&
                    !searchDTO.getStatus().isBlank()) {

                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(root.get("status")),
                                searchDTO.getStatus().toLowerCase()
                        )
                );
            }

            return predicates;
        };
    }
}
