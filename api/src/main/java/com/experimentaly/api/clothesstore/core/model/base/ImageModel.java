package com.experimentaly.api.clothesstore.core.model.base;

import java.util.UUID;
import javax.persistence.Lob;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"},
        justification = "there is not posibility with lombok")
public class ImageModel extends UserDateAuditModel<UUID> {

    private String name;

    @Lob
    private byte[] data;

}
