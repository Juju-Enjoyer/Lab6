package Command.CommandList.Exit;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;

import java.io.Serializable;

public class Exit implements Command, Serializable {
    private CollectionManager cm;
    public Exit (CollectionManager cm){
        this.cm=cm;
    }
    public Exit (){
    }
    @Override
    public String getName(){
        return "EXIT";
    }
    @Override
    public String getDescription(){
        return "завершить программу (без сохранения в файл)";
    }

    @Override
    public void setArgument(String argument) {

    }

    @Override
    public String getArgument() {
        return null;
    }

    public CollectionManager getCm() {
        return cm;
    }

    public void setCm(CollectionManager cm) {
        this.cm = cm;
    }

    @Override
    public boolean execute(String args) {
        boolean  res = cm.exit();

        return res;
    }


}
