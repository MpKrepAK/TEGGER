package org.marketplace.server.repository;

import org.marketplace.server.model.entity.Product;
import org.marketplace.server.model.entity.ProductTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
    Page<ProductTag> findByNameContaining(String name, Pageable pageable);
    Optional<ProductTag> findByName(String name);
    Long countByNameContaining(String name);
}
