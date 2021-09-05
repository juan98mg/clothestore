package com.experimentaly.api.clothesstore.application.rest.controller;

import com.experimentaly.api.clothesstore.application.rest.request.ProductListRequest;
import com.experimentaly.api.clothesstore.application.rest.request.ProductRequestSave;
import com.experimentaly.api.clothesstore.core.model.ProductModel;
import com.experimentaly.api.clothesstore.core.ports.input.ProductMapper;
import com.experimentaly.api.clothesstore.core.ports.input.ProductService;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.Popularity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "${app.api.version.v1}/products", method = RequestMethod.POST)
    public ProductModel save(@ModelAttribute ProductRequestSave request) {

        var model = mapper.convert(request);

        return service.save(model, request.getFiles(), request.getSelledCountry());

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "${app.api.version.v1}/products")
    public void update(@ModelAttribute ProductRequestSave request) {
        var model = mapper.convert(request);
        service.update(model, request.getFiles(), request.getSelledCountry());
    }

    @GetMapping(path = "${app.api.version.v1}/products")
    public Page<ProductModel> list(
            @RequestParam(value = "popularity", required = false) Popularity popularity,
            @RequestParam(value = "name", required = false) String likeName,
            @RequestParam(value = "lessDiscount", required = false,
                    defaultValue = "0.0") float lessDiscount,
            @RequestParam(value = "graterDiscount", required = false,
                    defaultValue = "0.0") float greaterDiscount,
            @RequestParam(value = "lessPrice", required = false,
                    defaultValue = "0.0") float lessPrice,
            @RequestParam(value = "graterPrice", required = false,
                    defaultValue = "0.0") float greaterPrice,
            @RequestParam(value = "deleted", required = false,
                    defaultValue = "false") boolean deleted,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "100") int size) {


        var dto = new ProductListRequest(likeName, popularity, lessDiscount, greaterDiscount,
                lessPrice, greaterPrice, deleted, page, size, country);


        return this.service.list(dto);

    }



}
