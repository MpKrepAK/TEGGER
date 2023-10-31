package org.marketplace.server.repository;

import org.marketplace.server.model.entity.ComplaintType;
import org.marketplace.server.model.entity.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Page<Manufacturer> findByNameContaining(String name, Pageable pageable);
    Optional<Manufacturer> findByName(String name);
    Long countByNameContaining(String name);
}
