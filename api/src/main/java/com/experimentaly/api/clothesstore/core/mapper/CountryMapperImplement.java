package com.experimentaly.api.clothesstore.core.mapper;

import com.experimentaly.api.clothesstore.core.model.CountryModel;
import com.experimentaly.api.clothesstore.core.ports.input.CountryMapper;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.CountryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryMapperImplement implements CountryMapper {


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CountryModel convert(CountryEntity image) {

        return modelMapper.map(image, CountryModel.class);
    }

    @Override
    public CountryEntity convert(CountryModel image) {
        return modelMapper.map(image, CountryEntity.class);
    }

}
