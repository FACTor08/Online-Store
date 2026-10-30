package com.factor.shopOnline.exception;

public class ExistingDataException extends RuntimeException {
    public ExistingDataException(String message) {
        super(message);
    }
}
