package Command.CommandList.RemoveKey;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.Serializable;

public class RemoveKey implements Command, Serializable {

    private CollectionManager cm;
    private String argument;
    public RemoveKey(CollectionManager cm){
        this.cm=cm;
    }
    public RemoveKey(){}
    @Override
    public String getName() {
        return "REMOVEKEY";
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его ключу";
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

        return cm.removeKey(key);
    }
}
