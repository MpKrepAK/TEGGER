package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CardDTO {
    private long id;
    private String typeName;
    private String manufacturerName;
    private int count;
    private BigDecimal cost;
    private long rating;
    private String mainImage;
    private String title;

    public CardDTO() {
    }

    public CardDTO(long id, String typeName, String manufacturerName, int count, BigDecimal cost, long rating, String mainImage, String title) {
        this.id = id;
        this.typeName = typeName;
        this.manufacturerName = manufacturerName;
        this.count = count;
        this.rating = rating;
        this.mainImage = mainImage;
        this.title = title;
    }
}
