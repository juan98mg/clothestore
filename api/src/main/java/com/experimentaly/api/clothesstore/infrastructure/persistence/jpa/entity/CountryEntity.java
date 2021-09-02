package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.UserDateAuditEntity;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_countries")
public class CountryEntity extends UserDateAuditEntity {

    @Column(nullable = false)
    @Length(min = 2, max = 100)
    private String name;

    @Column(nullable = false)
    @Length(min = 1, max = 10)
    private String daneCode;

    @ManyToOne()
    private Set<ProductEntity> products;


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

}
