package Command.CommandList.Help;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;

import java.io.Serializable;

public class Help implements Command, Serializable {
    private CollectionManager cm;
    public Help(CollectionManager cm){
        this.cm=cm;
    }
    public Help(){

    }
    @Override
    public String getName(){
        return "HELP";
    }
    @Override
    public String getDescription(){
        return "вывести справку по доступным командам";
    }

    @Override
    public void setArgument(String argument) {
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
    public String execute(String args) {

        return cm.help();
    }
}