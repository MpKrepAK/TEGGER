package org.marketplace.server.repository;

import org.marketplace.server.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p " +
            "FROM Product p " +
            "JOIN p.tags t " +
            "WHERE t.name IN :tagNames " +
            "GROUP BY p " +
            "ORDER BY COUNT(t) DESC")
    List<Product> findTop20ByTagNamesOrderByTagCountDesc(@Param("tagNames") List<String> tagNames);
}
