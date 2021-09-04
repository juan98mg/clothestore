package com.experimentaly.api.clothesstore.core.exception;

public class BaseException extends RuntimeException {

    protected final String code;

    private static final long serialVersionUID = 1L;

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
