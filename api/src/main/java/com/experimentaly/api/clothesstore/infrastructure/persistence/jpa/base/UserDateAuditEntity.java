package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base;

import java.util.UUID;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

public class UserDateAuditEntity extends DateAuditEntity {

    @CreatedBy
    protected UUID createdBy;

    @LastModifiedBy
    protected UUID updatebleBy;

    protected UUID deletedBy;

    public UUID getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getUpdatebleBy() {
        return this.updatebleBy;
    }

    public void setUpdatebleBy(UUID updatebleBy) {
        this.updatebleBy = updatebleBy;
    }

    public UUID getDeletedBy() {
        return this.deletedBy;
    }

    public void setDeletedBy(UUID deletedBy) {
        this.deletedBy = deletedBy;
    }

}
