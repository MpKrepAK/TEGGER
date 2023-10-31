package org.marketplace.server.repository;

import org.marketplace.server.model.entity.ProductInfoNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoNumberRepository extends JpaRepository<ProductInfoNumber, Long> {
}
