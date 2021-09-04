package com.experimentaly.api.clothesstore.core.mapper;

import java.util.ArrayList;
import java.util.List;
import com.experimentaly.api.clothesstore.application.rest.request.ProductRequestSave;
import com.experimentaly.api.clothesstore.core.model.ProductModel;
import com.experimentaly.api.clothesstore.core.ports.input.CountryMapper;
import com.experimentaly.api.clothesstore.core.ports.input.ImageMapper;
import com.experimentaly.api.clothesstore.core.ports.input.ProductMapper;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperImplement implements ProductMapper {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public ProductModel convert(ProductRequestSave product) {

        return modelMapper.map(product, ProductModel.class);
    }

    @Override
    public ProductRequestSave convert(ProductModel product) {

        return modelMapper.map(product, ProductRequestSave.class);
    }

    @Override
    public ProductEntity convertEntity(ProductModel product) {

        return modelMapper.map(product, ProductEntity.class);

    }

    @Override
    public ProductModel convertEntity(ProductEntity product) {


        var model = modelMapper.map(product, ProductModel.class);
        model.setCountry(countryMapper.convert(product.getCountry()));
        model.setImages(imageMapper.convert(product.getImages()));

        return model;
    }

    @Override
    public List<ProductModel> convert(List<ProductEntity> products) {

        var listModel = new ArrayList<ProductModel>();

        if (products != null) {

            products.stream().forEach((product) -> {
                listModel.add(convertEntity(product));
            });
        }

        return listModel;
    }

    @Override
    public Page<ProductModel> convert(Page<ProductEntity> page) {

        var list = this.convert(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());

    }



}
