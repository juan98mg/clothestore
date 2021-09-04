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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @PostMapping(path = "${app.api.version.v1}/products")
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
    public Page<ProductModel> list(@RequestParam("popularity") Popularity popularity,
            @RequestParam("name") String likeName, @RequestParam("lessDiscount") float lessDiscount,
            @RequestParam("graterDiscount") float greaterDiscount,
            @RequestParam("lessPrice") float lessPrice,
            @RequestParam("graterPrice") float greaterPrice,
            @RequestParam("deleted") boolean deleted, @RequestParam("country") String country,
            @RequestParam("page") int page, @RequestParam("size") int size) {


        var dto = new ProductListRequest(likeName, popularity, lessDiscount, greaterDiscount,
                lessPrice, greaterPrice, deleted, page, size, country);


        return this.service.list(dto);

    }



}
