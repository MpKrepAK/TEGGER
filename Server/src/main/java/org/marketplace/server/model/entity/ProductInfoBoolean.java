package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_info_booleans")
@Getter
@Setter
public class ProductInfoBoolean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private InfoBoolean info;
    private Boolean value;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
