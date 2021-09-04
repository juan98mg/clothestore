package com.experimentaly.api.clothesstore.core.ports.input;

import com.experimentaly.api.clothesstore.core.model.CountryModel;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.CountryEntity;

public interface CountryMapper {

    CountryModel convert(CountryEntity image);

    CountryEntity convert(CountryModel image);

}
