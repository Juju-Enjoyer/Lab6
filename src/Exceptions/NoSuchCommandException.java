package Exceptions;

public class NoSuchCommandException extends Exception{
    public String getMessage() {
        return "Command with wrong argument or unknown command. Type \"help\" to get all commands with their name and description";
    }
}
