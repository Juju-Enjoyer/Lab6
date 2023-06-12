package Exceptions;

public class IllegalValueException extends Exception{
    private final String message;
    public IllegalValueException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
