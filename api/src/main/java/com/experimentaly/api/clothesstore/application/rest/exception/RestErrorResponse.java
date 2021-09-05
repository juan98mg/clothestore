package com.experimentaly.api.clothesstore.application.rest.exception;

import com.experimentaly.api.clothesstore.application.rest.response.ErrorResponse;
import com.experimentaly.api.clothesstore.core.exception.ImageRerenderException;
import com.experimentaly.api.clothesstore.core.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


public class RestErrorResponse {



    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(ValidationException ex,
            WebRequest request) {
        ErrorResponse error =
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ImageRerenderException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(
            ImageRerenderException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}
