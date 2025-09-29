package com.java.library.exceptions.custom;

public class ExemplarNotAvailableException extends RuntimeException {
    public ExemplarNotAvailableException(String message) {
        super(message);
    }

    public ExemplarNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
