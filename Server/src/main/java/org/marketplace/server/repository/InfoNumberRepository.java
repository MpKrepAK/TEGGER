package org.marketplace.server.repository;

import org.marketplace.server.model.entity.InfoNumber;
import org.marketplace.server.model.entity.ProductTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfoNumberRepository extends JpaRepository<InfoNumber, Long> {
    Page<InfoNumber> findByNameContaining(String name, Pageable pageable);
    Optional<InfoNumber> findByName(String name);
    Long countByNameContaining(String name);
}
