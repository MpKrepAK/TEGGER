package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketplace.server.model.entity.InfoBoolean;

@Getter
@Setter
@NoArgsConstructor
public class ProductInfoBooleanDTO {
    private long id;
    private String name;
    private Boolean value;

    public ProductInfoBooleanDTO(long id, String name, Boolean value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
