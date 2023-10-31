package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketplace.server.model.abstraction.InfoBaseAbstraction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "info_numbers")
@Getter
@Setter
@NoArgsConstructor
public class InfoNumber extends InfoBaseAbstraction {
    @OneToMany(mappedBy = "info", fetch = FetchType.LAZY)
    private Set<ProductInfoNumber> productInfoNumbers = new HashSet<>();

    public InfoNumber(Long id, String name) {
        super(id, name);
    }
}
