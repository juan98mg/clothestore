package com.experimentaly.api.clothesstore.core.business;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.experimentaly.api.clothesstore.core.exception.ValidationException;

public abstract class BaseService {


    protected <T> void validate(T input) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new ValidationException("ValidationError", violations.toString());
        }
    }

}
