package Command.CommandList.Insert;

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

public class Insert implements Command, Serializable, CommandWithFlat {
    private CollectionManager cm;
    private String argument;
    private Flat flat;
    public Insert(CollectionManager cm){
        this.cm=cm;
    }
    public Insert(){
    }
    @Override
    public void setArgument(String argument){
        //filler
        this.argument= String.valueOf(argument);
    }

    @Override
    public String getArgument(){
        return  argument;
    }
    @Override
    public String getName() {
        return "INSERT";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент с заданным ключом";
    }
    public CollectionManager getCm() {
        return cm;
    }

    public void setCm(CollectionManager cm) {
        this.cm = cm;
    }

    @Override
    public String execute(String args) throws NoSuchElementException, NumberFormatException, IllegalKeyException, IllegalValueException,NoSuchCommandException {
        if (args.isEmpty()){
            throw new NoSuchCommandException();
        }
//        else if (cm.getCollection().containsKey(Long.valueOf(args))){
//            throw new IllegalKeyException("уже есть квартира с таким номером\"");
//        }

        return cm.insert(this);
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
}
