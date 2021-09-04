package com.experimentaly.api.clothesstore.core.model.base;

import java.util.UUID;
import lombok.Data;

@Data
public class ImageModel extends UserDateAuditModel<UUID> {

    private String name;

    private byte[] data;

}
