package com.experimentaly.api.clothesstore.application.rest.request;

import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import com.experimentaly.api.clothesstore.core.util.AppConstants;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.Popularity;
import org.springframework.web.multipart.MultipartFile;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;


@Data
@NoArgsConstructor
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"},
        justification = "there is not posibility with lombok")
public class ProductRequestSave {

    private UUID id;
    @NotBlank(message = AppConstants.PRODUCT_NAME_NOT_VALID)
    private String name;
    private String description;
    @Positive(message = AppConstants.MIN_VALUE_PRICE_VALIDATION_ERROR)
    private float price;
    @Positive(message = AppConstants.MIN_VALUE_DISCOUNT_VALIDATION_ERROR)
    @Max(value = 100, message = AppConstants.MAX_VALUE_DISCOUNT_VALIDATION_ERROR)
    private float discount;
    private Popularity popularity = Popularity.ZERO;
    @NotBlank(message = AppConstants.PRODUCT_COUNTRY_MUST_NOT_BE_NULL)
    private String selledCountry;
    @Lob
    @With
    private MultipartFile[] files;



    public ProductRequestSave(UUID id, String name, String description, float price, float discount,
            Popularity popularity, String selledCountry, MultipartFile[] files) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.popularity = popularity;
        this.selledCountry = selledCountry;
        this.files = files;
    }


}
