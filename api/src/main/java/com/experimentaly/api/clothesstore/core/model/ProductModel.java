package com.experimentaly.api.clothesstore.core.model;

import java.util.Set;
import java.util.UUID;
import com.experimentaly.api.clothesstore.core.model.base.ImageModel;
import com.experimentaly.api.clothesstore.core.model.base.UserDateAuditModel;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.Popularity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductModel extends UserDateAuditModel<UUID> {

    private String name;
    private String description;
    private float price;
    private float discount;
    private Popularity popularity = Popularity.ZERO;
    private int timesSearched = 0;
    private float priceDiscount;
    private CountryModel country;
    private Set<ImageModel> images;


}
