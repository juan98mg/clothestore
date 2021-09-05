package com.experimentaly.api.clothesstore.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends BaseException {

    public ValidationException(String code, String message) {
        super(code, message);
    }

}
