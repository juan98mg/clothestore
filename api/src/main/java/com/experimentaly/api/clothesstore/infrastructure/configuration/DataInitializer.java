package com.experimentaly.api.clothesstore.infrastructure.configuration;

import java.util.Arrays;
import com.experimentaly.api.clothesstore.core.util.AppConstants;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.CountryEntity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void run(String... args) throws Exception {

        if (Arrays.binarySearch(args, AppConstants.POPULATE) != -1)
            createCountries();
    }


    private void createCountries() {


        var country = new CountryEntity();

        country.setName("COLOMBIA");
        country.setDaneCode("001");
        country.setMaximunDiscount(50);

        this.countryRepository.saveAndFlush(country);


        country = new CountryEntity();

        country.setName("MEXICO");
        country.setDaneCode("002");
        country.setMaximunDiscount(50);

        this.countryRepository.saveAndFlush(country);

        country = new CountryEntity();
        country.setName("CHILE");
        country.setDaneCode("003");
        country.setMaximunDiscount(30);

        this.countryRepository.saveAndFlush(country);

        country = new CountryEntity();
        country.setName("PERÚ");
        country.setDaneCode("004");
        country.setMaximunDiscount(30);

        this.countryRepository.saveAndFlush(country);

    }



}
