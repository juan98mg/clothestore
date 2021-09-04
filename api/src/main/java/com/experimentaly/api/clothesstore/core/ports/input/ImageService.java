package com.experimentaly.api.clothesstore.core.ports.input;

import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void save(MultipartFile[] files, ProductEntity product);

    void changeImages(MultipartFile[] files, ProductEntity productEntity);

}
