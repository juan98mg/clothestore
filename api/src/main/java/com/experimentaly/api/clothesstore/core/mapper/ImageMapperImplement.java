package com.experimentaly.api.clothesstore.core.mapper;

import java.util.HashSet;
import java.util.Set;
import com.experimentaly.api.clothesstore.core.model.base.ImageModel;
import com.experimentaly.api.clothesstore.core.ports.input.ImageMapper;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ImageEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageMapperImplement implements ImageMapper {


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ImageModel convert(ImageEntity image) {
        return modelMapper.map(image, ImageModel.class);
    }

    @Override
    public ImageEntity convert(ImageModel image) {
        return modelMapper.map(image, ImageEntity.class);
    }

    @Override
    public Set<ImageModel> convert(Set<ImageEntity> images) {
        var modelSet = new HashSet<ImageModel>();

        if (images != null) {
            images.stream().forEach(img -> 
                modelSet.add(convert(img))
            );
        }

        return modelSet;
    }

}
