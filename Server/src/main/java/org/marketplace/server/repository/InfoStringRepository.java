package org.marketplace.server.repository;

import org.marketplace.server.model.entity.InfoString;
import org.marketplace.server.model.entity.ProductTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfoStringRepository extends JpaRepository<InfoString, Long> {
    Page<InfoString> findByNameContaining(String name, Pageable pageable);
    Optional<InfoString> findByName(String name);
    Long countByNameContaining(String name);
}
