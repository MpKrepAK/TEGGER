package org.marketplace.server.model.abstraction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.marketplace.server.model.entity.ProductType;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class InfoBaseAbstraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
