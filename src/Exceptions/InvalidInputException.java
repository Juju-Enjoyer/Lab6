package Exceptions;

import java.io.Serializable;

public class InvalidInputException extends Exception implements Serializable {
    public String getMessage() {
        return "Invalid input. Work with the collection will be finished";
    }
}
