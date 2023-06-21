package Command.CommandList.RemoveGreater;

import Command.CollectionManager.CollectionManager;
import Command.CommandList.CommandWithFlat;
import Command.CommandProcessor.Command;
import Command.Parse.Filler;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;
import PossibleClassInCollection.Flat.Flat;

import java.io.Serializable;

public class RemoveGreater implements Command, Serializable, CommandWithFlat {
    private CollectionManager cm;
    private String argument;
    private Flat flat;
    public RemoveGreater(CollectionManager cm){
        this.cm=cm;
    }
    public RemoveGreater(){}
    @Override
    public String getName() {
        return "REMOVEGREATER";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, превышающие заданный";
    }

    @Override
    public void setArgument(String argument) throws NoSuchCommandException {
this.argument=argument;
    }

    @Override
    public String getArgument() {
        return argument;
    }

    @Override
    public CollectionManager getCm() {
        return cm;
    }

    @Override
    public void setCm(CollectionManager cm) {
this.cm=cm;
    }

    @Override
    public String execute(String args) throws NoSuchCommandException, IllegalKeyException, IllegalValueException {
       return cm.removeGreater(this.flat);
    }

    @Override
    public void setFlat() throws IllegalValueException {
        Filler pr = new Filler();
        flat = pr.parser(1);
    }

    @Override
    public Flat getFlat() {
        return flat;
    }
    @Override
    public void setFlatScript(Flat flat) {
        this.flat=flat;
    }
}
