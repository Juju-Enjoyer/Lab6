package Command.CommandList.UpdateById;

import Command.CollectionManager.CollectionManager;
import Command.CommandList.CommandWithFlat;
import Command.CommandProcessor.Command;
import Command.Parse.Filler;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;
import PossibleClassInCollection.Flat.Flat;

import java.io.Serializable;

public class UpdateById implements Command, Serializable, CommandWithFlat {
    private CollectionManager cm;
    private String argument;
    private Flat flat;
    public UpdateById(CollectionManager cm){
        this.cm=cm;
    }
    public UpdateById(){}

    @Override
    public String getName() {
        return "UPDATEBYID";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void setArgument(String argument){
        //filler
        this.argument= String.valueOf(argument);
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
        try {
            if (args.isEmpty()) {
                throw new NoSuchCommandException();
            }

            long key = Long.parseLong(args);
            return cm.update(this,key);}
        catch (NumberFormatException e){
            return ("String там где не надо");
        }
    }

    @Override
    public void setFlat() throws IllegalValueException {
        Filler pr = new Filler();
        flat = pr.parser(Long.parseLong(argument));
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
