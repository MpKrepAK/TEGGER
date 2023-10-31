package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private long id;
    private String typeName;
    private String manufacturerName;
    private int count;
    private long rating;
    private String mainImage;
    private String title;
    private String info;
    private Set<String> images = new HashSet<>();
    private Set<ProductInfoBooleanDTO> infoBooleans = new HashSet<>();
    private Set<ProductInfoNumberDTO> infoNumbers = new HashSet<>();
    private Set<ProductInfoStringDTO> infoStrings = new HashSet<>();

    public ProductDTO(long id,
                      String typeName,
                      String manufacturerName,
                      int count,
                      long rating,
                      String mainImage,
                      String title,
                      String info,
                      Set<String> images,
                      Set<ProductInfoBooleanDTO> infoBooleans,
                      Set<ProductInfoNumberDTO> infoNumbers,
                      Set<ProductInfoStringDTO> infoStrings) {
        this.id = id;
        this.typeName = typeName;
        this.manufacturerName = manufacturerName;
        this.count = count;
        this.rating = rating;
        this.mainImage = mainImage;
        this.title = title;
        this.info = info;
        this.images = images;
        this.infoBooleans = infoBooleans;
        this.infoNumbers = infoNumbers;
        this.infoStrings = infoStrings;
    }

}
