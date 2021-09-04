package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.experimentaly.api.clothesstore.core.util.AppConstants;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.Popularity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.UserDateAuditEntity;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_products")
public class ProductEntity extends UserDateAuditEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @Length(min = 3, max = 100, message = AppConstants.PRODUCT_NAME_NOT_VALID)
    private String name;
    @Column(nullable = false)
    @Length(min = 3, max = 500)
    private String description;
    @Column(nullable = false)
    @Min(value = 0, message = AppConstants.MIN_VALUE_PRICE_VALIDATION_ERROR)
    private float price;
    @Min(value = 0, message = AppConstants.MIN_VALUE_DISCOUNT_VALIDATION_ERROR)
    @Max(value = 100, message = AppConstants.MAX_VALUE_DISCOUNT_VALIDATION_ERROR)
    @Column(nullable = false)
    private float discount;
    @Enumerated(EnumType.STRING)
    private Popularity popularity = Popularity.ZERO;
    @Min(0)
    private int timesSearched = 0;
    @Formula("price-(price*(discount/100))")
    private float priceDiscount;

    @ManyToOne
    @JoinColumn(name = "country_sell_id", nullable = false)
    private CountryEntity country;


    @OneToMany(mappedBy = "product")
    private Set<ImageEntity> images;



    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return this.discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Popularity getPopularity() {
        return this.popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public int getTimesSearched() {
        return this.timesSearched;
    }

    public void setTimesSearched(int timesSearched) {
        this.timesSearched = timesSearched;
    }


    public CountryEntity getCountry() {
        return this.country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }


    public Set<ImageEntity> getImages() {
        return this.images;
    }

    public void setImages(Set<ImageEntity> images) {
        this.images = images;
    }

}
