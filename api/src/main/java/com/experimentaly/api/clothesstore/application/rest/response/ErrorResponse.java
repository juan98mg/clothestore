package com.experimentaly.api.clothesstore.application.rest.response;

import static com.experimentaly.api.clothesstore.core.util.DateUtil.DATE_TIME_FORMAT;
import java.time.LocalDateTime;
import java.util.List;
import com.experimentaly.api.clothesstore.core.util.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ErrorResponse {

    Logger logger = LoggerFactory.getLogger(ErrorResponse.class);
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private LocalDateTime timestamp;
    private String message;
    private String code;
    private List<ApiSubError> subErrors;

    private ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus status, String code) {
        this();
        this.status = status;
        this.code = code;
    }

    public ErrorResponse(HttpStatus status, String code, Throwable ex) {
        this();
        this.status = status;
        this.message = AppConstants.UNEXPECTED_ERROR;
        this.code = code;
        logger.error(code, ex);
    }

    public ErrorResponse(HttpStatus status, String message, String code, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.code = code;
        logger.error(code, ex);
    }

    public ErrorResponse(HttpStatus status, String message, String code) {
        this();
        this.status = status;
        this.message = message;
        this.code = code;
    }



}
