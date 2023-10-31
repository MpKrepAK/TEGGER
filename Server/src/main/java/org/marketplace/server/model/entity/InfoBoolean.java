package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketplace.server.model.abstraction.InfoBaseAbstraction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "info_booleans")
@Getter
@Setter
@NoArgsConstructor
public class InfoBoolean extends InfoBaseAbstraction {
    @OneToMany(mappedBy = "info", fetch = FetchType.LAZY)
    private Set<ProductInfoBoolean> productInfoBooleans = new HashSet<>();

    public InfoBoolean(Long id, String name) {
        super(id, name);
    }
}
