package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.UserDateAuditEntity;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_countries")
public class CountryEntity extends UserDateAuditEntity implements Serializable {

    private static final long serialVersionUID = 9123544141278441956L;

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    @Length(min = 2, max = 100)
    private String name;

    @Column(nullable = false, unique = true)
    @Length(min = 1, max = 10)
    private String daneCode;

    @OneToMany(mappedBy = "country")
    private Set<ProductEntity> products;

    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private int maximunDiscount;


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

    public String getDaneCode() {
        return this.daneCode;
    }

    public void setDaneCode(String daneCode) {
        this.daneCode = daneCode;
    }


    public Set<ProductEntity> getProducts() {
        return this.products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }



    public int getMaximunDiscount() {
        return this.maximunDiscount;
    }

    public void setMaximunDiscount(int maximunDiscount) {
        this.maximunDiscount = maximunDiscount;
    }


}
