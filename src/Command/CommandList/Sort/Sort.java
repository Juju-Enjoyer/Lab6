package Command.CommandList.Sort;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.Serializable;

public class Sort implements Command, Serializable {
    private CollectionManager cm;
    private String argument;
    public Sort(CollectionManager cm){
        this.cm=cm;
    }
    public Sort(){}
    @Override
    public String getName() {
        return "SORT";
    }

    @Override
    public String getDescription() {
        return "сокращение для print_field_ascending_house вывести значения поля house всех элементов в порядке возрастания";
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
    public CollectionManager getCm() {
        return cm;
    }

    @Override
    public void setCm(CollectionManager cm) {
this.cm=cm;
    }

    @Override
    public String execute(String args) throws NoSuchCommandException, IllegalKeyException, IllegalValueException {
        if (!args.isEmpty()){
            throw new NoSuchCommandException();
        }
        return cm.sort();
    }
}
