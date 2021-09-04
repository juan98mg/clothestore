package com.experimentaly.api.clothesstore.core.business;


import com.experimentaly.api.clothesstore.application.rest.request.ProductRequestList;
import com.experimentaly.api.clothesstore.application.rest.request.ProductRequestSave;
import com.experimentaly.api.clothesstore.core.exception.ValidationException;
import com.experimentaly.api.clothesstore.core.model.ProductModel;
import com.experimentaly.api.clothesstore.core.ports.input.ImageService;
import com.experimentaly.api.clothesstore.core.ports.input.ProductMapper;
import com.experimentaly.api.clothesstore.core.ports.input.ProductService;
import com.experimentaly.api.clothesstore.core.util.AppConstants;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.CountryEntity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository.CountryRepository;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImplement implements ProductService {


    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ImageService imageService;

    @Override
    @Transactional
    public ProductModel save(ProductModel product, MultipartFile[] files, String countrySelled) {

        var country = getCountryByName(countrySelled);
        var msn = validateToSave(product);
        msn += validateDiscountByCountry(countrySelled, product.getDiscount(), country);

        if (!"".equals(msn))
            throw new ValidationException(AppConstants.PRODUCT_VALIDATION_ERROR_CODE, msn);

        var productEntity = mapper.convertEntity(product);
        productEntity.setCountry(country);

        productEntity = repository.saveAndFlush(productEntity);

        imageService.save(files, productEntity);

        return mapper.convertEntity(productEntity);
    }

    @Override
    public void update(ProductRequestSave product) {
        // TODO Auto-generated method stub

    }

    @Override
    public void list(ProductRequestList dto) {
        // TODO Auto-generated method stub

    }


    private String validateToSave(ProductModel model) {

        var msn = "";
        if (model.getId() != null) {
            msn += AppConstants.ID_TO_SAVE_DEFINED_ERROR;
        }

        return msn;
    }

    private CountryEntity getCountryByName(String country) {

        if (country == null || "".equals(country))
            throw new ValidationException(AppConstants.PRODUCT_VALIDATION_ERROR_CODE,
                    AppConstants.SHOULD_ESPECIFY_COUNTRY);

        country = country.toUpperCase();

        var optionalCountry = countryRepository.findByNameAndDeletedAtIsNull(country);

        if (!optionalCountry.isPresent()) {
            var msn = String.format(AppConstants.CLOTHES_NOT_SELL_ON_THIS_COUNTRY, country);
            throw new ValidationException(AppConstants.PRODUCT_VALIDATION_ERROR_CODE, msn);
        }

        return optionalCountry.get();
    }

    private String validateDiscountByCountry(String country, float definedDiscount,
            CountryEntity countryDb) {


        var msn = "";

        if (countryDb.getMaximunDiscount() < definedDiscount)
            msn += String.format(AppConstants.DISCOUNT_NOT_VALID_BY_COUNTRY, definedDiscount,
                    country);

        return msn;

    }

}
