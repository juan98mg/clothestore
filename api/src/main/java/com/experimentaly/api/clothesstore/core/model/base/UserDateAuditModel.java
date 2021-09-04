package com.experimentaly.api.clothesstore.core.model.base;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class UserDateAuditModel<T> extends DateAuditModel<T> {

    protected UUID updatedBy;
    protected UUID deletedBy;
    protected UUID createdBy;

}

