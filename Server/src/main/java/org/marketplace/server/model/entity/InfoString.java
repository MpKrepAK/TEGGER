package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketplace.server.model.abstraction.InfoBaseAbstraction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "info_strings")
@Getter
@Setter
@NoArgsConstructor
public class InfoString extends InfoBaseAbstraction {
    @OneToMany(mappedBy = "info", fetch = FetchType.LAZY)
    private Set<ProductInfoString> productInfoStrings = new HashSet<>();

    public InfoString(Long id, String name) {
        super(id, name);
    }
}
