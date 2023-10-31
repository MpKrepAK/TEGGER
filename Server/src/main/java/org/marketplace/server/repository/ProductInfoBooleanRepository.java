package org.marketplace.server.repository;

import org.marketplace.server.model.entity.ProductInfoBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoBooleanRepository extends JpaRepository<ProductInfoBoolean, Long> {
}
