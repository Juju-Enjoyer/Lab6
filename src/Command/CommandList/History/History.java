package Command.CommandList.History;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.NoSuchCommandException;

import java.io.Serializable;

public class History implements Command, Serializable {
    private CollectionManager cm;
    private String  argument;
    public History(CollectionManager cm){
        this.cm=cm;
    }
    public History(){}
    @Override
    public String getName() {
        return "HISTORY";
    }

    @Override
    public String getDescription() {
        return "вывести последние команды";
    }

    @Override
    public void setArgument(String argument) throws NoSuchCommandException {
        if (!argument.isEmpty()){
            throw new NoSuchCommandException();
        }
        else {
            this.argument=argument;
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
    public String execute(String args) {
        return cm.history();
    }
}
