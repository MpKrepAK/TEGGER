package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComplaintDTO {
    private Long id;
    private OneFieldEntityDTO type;
    private String message;
    private long productId;

    public ComplaintDTO(Long id, OneFieldEntityDTO type, String message, long productId) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.productId = productId;
    }
}
