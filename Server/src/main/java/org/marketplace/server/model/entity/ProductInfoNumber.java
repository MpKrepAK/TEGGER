package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_info_numbers")
@Getter
@Setter
public class ProductInfoNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private InfoNumber info;
    private Double value;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
