package com.experimentaly.api.clothesstore.core.model;

import java.util.UUID;
import com.experimentaly.api.clothesstore.core.model.base.UserDateAuditModel;
import lombok.Data;

@Data
public class CountryModel extends UserDateAuditModel<UUID> {

    private String name;
    private String daneCode;
    private int maximunDiscount;


}
