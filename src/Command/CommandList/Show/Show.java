package Command.CommandList.Show;

import Command.CollectionManager.CollectionManager;
import Command.CommandList.CommandWithFlat;
import Command.CommandProcessor.Command;
import Command.Parse.Filler;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;
import PossibleClassInCollection.Flat.Flat;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class Show implements Command, Serializable {
    private CollectionManager cm;
    private String argument;
    private Flat flat;

    public Show(CollectionManager cm) {
        this.cm = cm;
    }

    public Show() {
    }

    @Override
    public void setArgument(String argument) {
        //filler
        this.argument = String.valueOf(argument);
    }

    @Override
    public String getArgument() {
        return argument;
    }

    @Override
    public String getName() {
        return "SHOW";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    public CollectionManager getCm() {
        return cm;
    }

    public void setCm(CollectionManager cm) {
        this.cm = cm;
    }

    @Override
    public String execute(String args) throws NoSuchElementException, NumberFormatException, IllegalKeyException, IllegalValueException, NoSuchCommandException {
        return cm.show();
    }
}
