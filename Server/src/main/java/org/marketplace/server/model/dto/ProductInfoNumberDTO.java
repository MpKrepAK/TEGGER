package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductInfoNumberDTO {
    private long id;
    private String name;
    private Double value;

    public ProductInfoNumberDTO(long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
