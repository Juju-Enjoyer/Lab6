package Command.CommandList.CountLessThanNumberOfRooms;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.Serializable;

public class CountLessThanNumberOfRooms implements Command, Serializable {
    private CollectionManager cm;
    private String argument;
    public CountLessThanNumberOfRooms(CollectionManager cm){
        this.cm=cm;
    }
    public CountLessThanNumberOfRooms(){}
    @Override
    public String getName() {
        return "COUNTLESSTHANNUMBEROFROOMS";
    }

    @Override
    public String getDescription() {
        return "вывести количество элементов, значение поля numberOfRooms которых меньше заданного";
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
        return cm.countLessThanNumberOfRooms(key);

    }
}
