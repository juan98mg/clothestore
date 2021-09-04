package com.experimentaly.api.clothesstore.application.rest.controller;

import com.experimentaly.api.clothesstore.application.rest.request.ProductRequestSave;
import com.experimentaly.api.clothesstore.core.model.ProductModel;
import com.experimentaly.api.clothesstore.core.ports.input.ProductMapper;
import com.experimentaly.api.clothesstore.core.ports.input.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


}
