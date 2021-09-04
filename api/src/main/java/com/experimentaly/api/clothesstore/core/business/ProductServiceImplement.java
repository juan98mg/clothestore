package com.experimentaly.api.clothesstore.core.business;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.experimentaly.api.clothesstore.application.rest.request.ProductListRequest;
import com.experimentaly.api.clothesstore.core.exception.ValidationException;
import com.experimentaly.api.clothesstore.core.model.ProductModel;
import com.experimentaly.api.clothesstore.core.ports.input.ImageService;
import com.experimentaly.api.clothesstore.core.ports.input.ProductMapper;
import com.experimentaly.api.clothesstore.core.ports.input.ProductService;
import com.experimentaly.api.clothesstore.core.util.AppConstants;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.Popularity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.CountryEntity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ProductEntity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository.CountryRepository;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImplement extends BaseService implements ProductService {


    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ImageService imageService;

    @Value("${app.api.product.list.increment-popularity-name}")
    private Long incrementByName;
    @Value("${app.api.product.list.increment-popularity-price}")
    private Long incrementByPrice;
    @Value("${app.api.product.list.increment-popularity-popularity}")
    private Long incrementByPopularity;
    @Value("${app.api.product.list.increment-popularity-country}")
    private Long incrementByCountry;
    @Value("${app.api.product.list.increment-popularity-discount}")
    private Long incrementByDiscount;

    @Value("${app.api.product.popularity-highest}")
    private int highestPopularity;

    @Value("${app.api.product.popularity-high}")
    private int hightPopularity;

    @Value("${app.api.product.popularity-medium}")
    private int mediumPopularity;

    @Value("${app.api.product.popularity-low}")
    private int lowPopularity;


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
    public void update(ProductModel model, MultipartFile[] files, String selledCountry) {
        var country = getCountryByName(selledCountry);
        var msn = validateToUpdate(model);
        msn += validateDiscountByCountry(selledCountry, model.getDiscount(), country);

        if (!"".equals(msn))
            throw new ValidationException(AppConstants.PRODUCT_VALIDATION_ERROR_CODE, msn);

        var productEntity = mapper.convertEntity(model);
        productEntity.setCountry(country);

        productEntity = repository.saveAndFlush(productEntity);

        imageService.changeImages(files, productEntity);



    }

    private String validateToUpdate(ProductModel model) {

        var msn = "";

        if (model.getId() == null) {
            msn += AppConstants.ID_TO_UPDATE_DEFINED_ERROR;
        } else if (!this.repository.existsById(model.getId())) {
            msn += AppConstants.UPDATING_NOT_EXISTS_ELEMENT;
        }

        return msn;
    }

    @Override
    @Transactional
    public Page<ProductModel> list(ProductListRequest dto) {

        validateToList(dto);

        var pageable = PageRequest.of(dto.getPage(), dto.getSize());

        var page = this.repository.findAll((root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (dto.getName() != null && !"".equals(dto.getName()))
                predicates.add(builder.like(root.get("name"), "%" + dto.getName() + ""));

            addGreaterLessPredicates(dto.getGreaterDiscount(), dto.getLessDiscount(), predicates,
                    "discount", builder, root);

            addGreaterLessPredicates(dto.getGraterPrice(), dto.getLessPrice(), predicates, "price",
                    builder, root);

            if (dto.getPopularity() != null)
                predicates.add(builder.equal(root.get("popularity"), dto.getPopularity()));

            predicates.add(dto.isDeleted() ? builder.isNotNull(root.get("deletedAt"))
                    : builder.isNull(root.get("deletedAt")));

            if (dto.getCountry() != null && !"".equals(dto.getCountry()))
                predicates.add(builder.like(root.get("country").get("name"),
                        "%" + dto.getCountry() + "%"));


            return builder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        updatePopularity(dto, page.getContent());
        return this.mapper.convert(page);

    }

    @Async
    public void updatePopularity(ProductListRequest dto, List<ProductEntity> content) {


        if (!dto.getPopularity().equals(Popularity.HIGHEST) && !content.isEmpty()) {

            var avg = this.repository.getAveragePopularity();
            content.stream().forEach(product -> {

                incrementTimesSearched(dto, product);

                redefinePopularity(avg, product);

                this.repository.save(product);

            });

        }
    }

    private void redefinePopularity(float avg, ProductEntity product) {
        var searchedtime = product.getSearchedTimes();
        if (searchedtime > (avg + highestPopularity))
            product.setPopularity(Popularity.HIGHEST);
        else if (searchedtime > (avg + hightPopularity))
            product.setPopularity(Popularity.HIGHEST);
        else if (searchedtime > (avg + mediumPopularity))
            product.setPopularity(Popularity.MEDIUM);
        else if (searchedtime > (avg + lowPopularity))
            product.setPopularity(Popularity.LOW);
    }

    private void incrementTimesSearched(ProductListRequest dto, ProductEntity product) {
        var searchedtime = product.getSearchedTimes();
        if (dto.getName() != null && dto.getName().length() > 3
                && product.getName().contains(dto.getName())) {
            searchedtime += incrementByName;
        }

        if (dto.getLessPrice() > 0 || dto.getGraterPrice() > 0) {
            searchedtime += incrementByPrice;
        }

        if (dto.getLessDiscount() > 0 || dto.getGreaterDiscount() > 0) {
            searchedtime += incrementByDiscount;
        }

        if (dto.getCountry() != null && !"".equals(dto.getCountry())) {
            searchedtime += incrementByCountry;
        }

        product.setSearchedTimes(searchedtime);
    }

    private void validateToList(ProductListRequest dto) {
        if (dto == null)
            throw new ValidationException(AppConstants.LIST_DTO_IS_NULL_VALIDATION_ERROR_CODE,
                    AppConstants.LIST_DTO_IS_NULL_VALIDATION_ERROR);
        this.validate(dto);
    }

    private void addGreaterLessPredicates(float greaterValue, float lessValue,
            List<Predicate> predicates, String fieldName, CriteriaBuilder builder,
            Root<ProductEntity> root) {

        predicates.add(builder.greaterThan(root.get(fieldName), greaterValue));

        predicates.add(builder.lessThan(root.get(fieldName), lessValue));


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
