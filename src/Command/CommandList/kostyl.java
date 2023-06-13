package Command.CommandList;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.Serializable;

public class kostyl implements Serializable, Command {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setArgument(String argument) {

    }

    @Override
    public String getArgument() {
        return null;
    }

    @Override
    public CollectionManager getCm() {
        return null;
    }

    @Override
    public void setCm(CollectionManager cm) {

    }

    @Override
    public String execute(String args) throws NoSuchCommandException, IllegalKeyException, IllegalValueException {
        return null;
    }
}
