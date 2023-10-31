package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductInfoStringDTO {
    private long id;
    private String name;
    private String value;

    public ProductInfoStringDTO(long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
