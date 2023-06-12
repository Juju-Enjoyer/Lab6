package Command.Parse;

import Command.CommandProcessor.Command;
import Exceptions.NoSuchCommandException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Reader {

    private ArrayList<String> allInputs = new ArrayList<>();
    private ArrayList <String> allCommands = new ArrayList<>();
    private Map<String, Command> commands;
    public Reader(Map<String, Command> commands){
        this.commands=commands;
    }

    Scanner sc = new Scanner(System.in);

    public String[] separator (String...agrs) throws NoSuchCommandException {
        String inputs = sc.nextLine().trim();
        String[] tokens = inputs.split(" ");
        String command = tokens[0];
        for(String coom: commands.keySet()){
            if ((command.toUpperCase()).equals(coom)){
                this.allCommands.add(command);
            }
        }
        return tokens;
    }

    public ArrayList<String> getAllCommands() {
        return allCommands;
    }

}
