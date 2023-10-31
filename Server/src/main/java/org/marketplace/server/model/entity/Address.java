package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String homeNumber;
    private String homeIndex;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();
}
