package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.UserDateAuditEntity;

@Entity
@Table(name = "tbl_images")
public class ImageEntity extends UserDateAuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 200)
    private String name;

    @Lob
    private byte[] data;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    public ImageEntity() {}

    public ImageEntity(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public byte[] getData() {
        return data;
    }



    public ProductEntity getProduct() {
        return this.product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }


    public void setData(byte[] data) {
        this.data = data;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ImageEntity other = (ImageEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
