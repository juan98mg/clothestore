package com.experimentaly.api.clothesstore.application.rest.request;

import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import com.experimentaly.api.clothesstore.core.util.AppConstants;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.Popularity;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
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
    private MultipartFile[] files;


}
