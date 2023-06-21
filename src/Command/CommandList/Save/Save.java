package Command.CommandList.Save;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.File;
import java.io.Serializable;

public class Save implements Command {
    private CollectionManager cm;
    public Save(CollectionManager cm){
        this.cm=cm;
    }

    @Override
    public String getName() {
        return "SAVE";
    }

    @Override
    public String getDescription() {
        return "без аргумента - сохранить коллекцию в файл, с аргументов - а назначенный файл";
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
        return null;
    }

    @Override
    public void setCm(CollectionManager cm) {

    }

    @Override
    public String execute(String args) throws NoSuchCommandException, IllegalKeyException, IllegalValueException {
        if (args.contains("/")){
           return cm.save(args);
        }
        else if(!args.contains("/")&(!args.isEmpty()) ){
            File file = new File(args);
           return cm.save(file);
        }
        else if ((args.equals(""))||args.equals(null)) {
           return cm.save();
        }
return null;
    }
}
