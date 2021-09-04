package com.experimentaly.api.clothesstore.core.ports.input;

import java.util.Set;
import com.experimentaly.api.clothesstore.core.model.base.ImageModel;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ImageEntity;

public interface ImageMapper {



    ImageModel convert(ImageEntity image);

    ImageEntity convert(ImageModel image);

    Set<ImageModel> convert(Set<ImageEntity> image);

}
