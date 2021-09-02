package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.UserDateAuditEntity;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_countries")
public class CountryEntity extends UserDateAuditEntity {


    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @Length(min = 2, max = 100)
    private String name;

    @Column(nullable = false)
    @Length(min = 1, max = 10)
    private String daneCode;

    @OneToMany(mappedBy = "country")
    private Set<ProductEntity> products;



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



}
