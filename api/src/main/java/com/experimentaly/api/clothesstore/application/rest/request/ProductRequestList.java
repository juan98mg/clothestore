package com.experimentaly.api.clothesstore.application.rest.request;

import java.util.UUID;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.Popularity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestList {

    private UUID id;
    private String name;
    private String description;
    private float price;
    private float discount;
    private Popularity popularity = Popularity.ZERO;
    private int timesSearched = 0;
    private float priceDiscount;

}
