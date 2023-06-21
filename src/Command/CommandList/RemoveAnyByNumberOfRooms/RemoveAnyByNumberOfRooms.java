package Command.CommandList.RemoveAnyByNumberOfRooms;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.Serializable;

public class RemoveAnyByNumberOfRooms implements Command, Serializable {
    private CollectionManager cm;
    private String argument;
    public RemoveAnyByNumberOfRooms(CollectionManager cm){
        this.cm=cm;
    }
    public RemoveAnyByNumberOfRooms(){
    }
    @Override
    public String getName() {
        return "REMOVEANYBYNUMBEROFROOMS";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции один элемент, значение поля numberOfRooms которого эквивалентно заданному";
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
        if (args.isEmpty()){
            throw new NoSuchCommandException();
        }
        int key = Integer.parseInt(args);
        return cm.removeAnyByNumberOfRooms(key);
    }
}

