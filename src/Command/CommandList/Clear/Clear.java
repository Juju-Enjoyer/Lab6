package Command.CommandList.Clear;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.Serializable;

public class Clear implements Command, Serializable {
    private CollectionManager cm;
    private String argument;
    public Clear(CollectionManager cm){
        this.cm=cm;
    }
    public Clear(){}
    @Override
    public String getName() {
        return "CLEAR";
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }

    @Override
    public void setArgument(String argument) throws NoSuchCommandException {
        if(!argument.isEmpty()){
            throw new NoSuchCommandException();
        }
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
        if (cm.getCollection().size()==0){
            return ("коллекция и так пуста");
        }
        else {
        return cm.clear();
        }
    }
}
