package org.marketplace.server.repository;

import org.marketplace.server.model.entity.ProductInfoString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoStringRepository extends JpaRepository<ProductInfoString, Long> {
}
