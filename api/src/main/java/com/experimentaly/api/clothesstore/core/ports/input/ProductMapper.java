package com.experimentaly.api.clothesstore.core.ports.input;

import java.util.List;
import com.experimentaly.api.clothesstore.application.rest.request.ProductRequestSave;
import com.experimentaly.api.clothesstore.core.model.ProductModel;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ProductEntity;
import org.springframework.data.domain.Page;

public interface ProductMapper {

    ProductModel convert(ProductRequestSave product);

    ProductRequestSave convert(ProductModel product);

    ProductEntity convertEntity(ProductModel product);

    ProductModel convertEntity(ProductEntity product);

    List<ProductModel> convert(List<ProductEntity> product);

    Page<ProductModel> convert(Page<ProductEntity> page);

}
