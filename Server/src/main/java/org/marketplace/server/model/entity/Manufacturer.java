package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "manufacturer")
@Getter
@Setter
@NoArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    public Manufacturer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
