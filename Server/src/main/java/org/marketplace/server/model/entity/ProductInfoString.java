package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_info_strings")
@Getter
@Setter
public class ProductInfoString {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private InfoString info;
    private String value;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
