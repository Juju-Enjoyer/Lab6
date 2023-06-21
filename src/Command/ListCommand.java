package Command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import Command.CommandList.Clear.Clear;
import Command.CommandList.CountLessThanNumberOfRooms.CountLessThanNumberOfRooms;
import Command.CommandList.ExecuteScript.ExecuteScriptNewEra;
import Command.CommandList.Exit.Exit;
import Command.CommandList.Help.Help;
import Command.CommandList.History.History;
import Command.CommandList.Info.Info;
import Command.CommandList.Insert.Insert;
import Command.CommandList.RemoveAnyByNumberOfRooms.RemoveAnyByNumberOfRooms;
import Command.CommandList.RemoveGreater.RemoveGreater;
import Command.CommandList.RemoveKey.RemoveKey;
import Command.CommandList.RemoveLower.RemoveLower;
import Command.CommandList.Show.Show;
import Command.CommandList.Sort.Sort;
import Command.CommandList.UpdateById.UpdateById;
import Command.CommandProcessor.Command;
import Exceptions.NoSuchCommandException;


public class ListCommand implements Serializable {
    private Map<String, Command> commands;


    public ListCommand() {
        commands = new TreeMap<>();
        Command cmd = new Exit();
        commands.put(cmd.getName(), cmd);
        cmd = new Insert();
        commands.put(cmd.getName(),cmd);
        cmd = new Help();
        commands.put(cmd.getName(), cmd);
        cmd = new Show();
        commands.put(cmd.getName(), cmd);
        cmd = new UpdateById();
        commands.put(cmd.getName(),cmd);
        cmd = new Clear();
        commands.put(cmd.getName(),cmd);
        cmd = new CountLessThanNumberOfRooms();
        commands.put(cmd.getName(),cmd);
        cmd = new History();
        commands.put(cmd.getName(),cmd);
        cmd = new Info();
        commands.put(cmd.getName(),cmd);
        cmd = new RemoveAnyByNumberOfRooms();
        commands.put(cmd.getName(),cmd);
        cmd = new RemoveGreater();
        commands.put(cmd.getName(),cmd);
        cmd = new RemoveLower();
        commands.put(cmd.getName(),cmd);
        cmd = new RemoveKey();
        commands.put(cmd.getName(),cmd);
        cmd = new Sort();
        commands.put(cmd.getName(),cmd);
        cmd = new ExecuteScriptNewEra();
        commands.put(cmd.getName(),cmd);

    }

    public Map<String, Command> getCommands() {
        return commands;
    }
    public boolean checkCommands(String command) throws NoSuchCommandException {
        if((commands.get(command.toUpperCase()) == null) | (command.equals(""))){
            throw new NoSuchCommandException();
        }
        else {return true;}
    }
    public Command<?> returnCommand(String command){
        return commands.get(command.toUpperCase());
    }

}
