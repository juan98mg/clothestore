package com.experimentaly.api.clothesstore.core.ports.input;

import com.experimentaly.api.clothesstore.application.rest.request.ProductListRequest;
import com.experimentaly.api.clothesstore.application.rest.request.ProductRequestSave;
import com.experimentaly.api.clothesstore.core.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    ProductModel save(ProductModel product, MultipartFile[] files, String countrySelled);

    void update(ProductRequestSave product);

    Page<ProductModel> list(ProductListRequest dto);
}
