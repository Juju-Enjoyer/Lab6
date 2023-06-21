package Command.CommandList.Info;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.Serializable;

public class Info implements Command, Serializable {

    private CollectionManager cm;
    public Info(CollectionManager cm){
        this.cm=cm;
    }
    public Info(){}
    @Override
    public String getName() {
        return "INFO";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции";
    }

    @Override
    public void setArgument(String argument) throws NoSuchCommandException {

    }

    @Override
    public String getArgument() {
        return null;
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
        return cm.info();
    }
}
