package Command.CommandProcessor;

import Command.CollectionManager.CollectionManager;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

public interface Command<T> {
    String getName();
    String getDescription();

    void setArgument (String argument);

    String getArgument();
    CollectionManager getCm();
    void setCm(CollectionManager cm);

    boolean execute(String args) throws NoSuchCommandException, IllegalKeyException, IllegalValueException;
}
