package com.signalquest.api;

/**
 * API exceptions with developer-friendly messages.
 */
public class ApiException extends Exception {
    private final String message;

    ApiException(String message) {
        this.message = message;
    }

    /**
     * The developer-friendly exception message; not intended for displaying to end-users
     */
    @Override
    public String getMessage() {
        return message;
    }

}
