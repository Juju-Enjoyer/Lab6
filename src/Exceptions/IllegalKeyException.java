package Exceptions;

import java.io.Serializable;

public class IllegalKeyException extends Exception implements Serializable {
    private final String message;

    public IllegalKeyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}