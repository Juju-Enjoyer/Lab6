package Exceptions;

import java.io.Serializable;

public class IllegalValueException extends Exception implements Serializable {
    private final String message;
    public IllegalValueException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
