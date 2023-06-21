package Command.CommandProcessor;

import Command.CollectionManager.CollectionManager;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

public interface Command<T> {
    CollectionManager cm = null;
    String argument = null;
    String getName();
    String getDescription();

    void setArgument (String argument) throws NoSuchCommandException;

    String getArgument();
    CollectionManager getCm();
    void setCm(CollectionManager cm);

    String execute(String args) throws NoSuchCommandException, IllegalKeyException, IllegalValueException;
}
