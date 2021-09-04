package com.experimentaly.api.clothesstore.application.rest.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.experimentaly.api.clothesstore.core.util.AppConstants;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.Popularity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductListRequest {

    private String name;
    private Popularity popularity = Popularity.ZERO;
    @Min(value = 0, message = AppConstants.MIN_VALUE_DISCOUNT_LESS_VALIDATION_ERROR)
    @Max(value = 100, message = AppConstants.MAX_VALUE_DISCOUNT_LESS_VALIDATION_ERROR)
    private float lessDiscount;
    @Min(value = 0, message = AppConstants.MIN_VALUE_DISCOUNT_GREATER_VALIDATION_ERROR)
    @Max(value = 100, message = AppConstants.MAX_VALUE_DISCOUNT_GREATER_VALIDATION_ERROR)
    private float greaterDiscount;
    @Min(value = 0, message = AppConstants.MIN_VALUE_PRICE_GREATER_VALIDATION_ERROR)
    private float lessPrice;
    @Min(value = 0, message = AppConstants.MIN_VALUE_PRICE_GREATER_VALIDATION_ERROR)
    private float graterPrice;
    private boolean deleted;
    @Min(value = 0, message = AppConstants.MIN_VALUE_PAGE_VALIDATION_ERROR)
    private int page;
    @Min(value = 1, message = AppConstants.MIN_VALUE_SIZE_VALIDATION_ERROR)
    private int size;
    private String country;



}
