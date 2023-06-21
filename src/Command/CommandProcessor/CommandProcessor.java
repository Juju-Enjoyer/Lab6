/*package Command.CommandProcessor;

//import Command.CollectionManager.CollectionManager;
//import Command.CommandList.Add.Add;
//import Command.CommandList.Clear.Clear;
//import Command.CommandList.CountLessThanNumberOfRooms.CountLessThanNumberOfRooms;
//import Command.CommandList.ExecuteScript.ExecuteScript;
//import Command.CommandList.Exit.Exit;
//import Command.CommandList.Help.Help;
//import Command.CommandList.History.History;
//import Command.CommandList.Info.Info;
//import Command.CommandList.Insert.Insert;
//import Command.CommandList.RemoveAnyByNumberOfRooms.RemoveAnyByNumberOfRooms;
//import Command.CommandList.RemoveGreater.RemoveGreater;
//import Command.CommandList.RemoveKey.RemoveKey;
//import Command.CommandList.Save.Save;
//import Command.CommandList.Show.Show;
//import Command.CommandList.Sort.Sort;
//import Command.CommandList.UpdateById.UpdateById;
//import Exceptions.IllegalKeyException;
//import Exceptions.IllegalValueException;
//import Exceptions.InvalidInputException;
//import Exceptions.NoSuchCommandException;

//import java.io.IOException;
//import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class CommandProcessor {

    private Map<String, Command> commands;
    CollectionManager cm = new CollectionManager(commands);


    public CommandProcessor() throws Exception {

        commands = new TreeMap<>();
        Command cmd = new Exit(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Insert(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Add(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Help(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Show(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new History(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new UpdateById(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new RemoveKey(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Clear(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new RemoveAnyByNumberOfRooms(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new CountLessThanNumberOfRooms(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new RemoveGreater(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Sort(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Info(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Save(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new ExecuteScript(cm);
        commands.put(cmd.getName(), cmd);
        cm.fullCommadsList(commands);



    }


    public void execute(String arg) throws InvalidInputException, IllegalValueException, NoSuchCommandException, IOException, InterruptedException {
        boolean result = true;
        try {
            System.out.println("Hi");
            cm.getWorkerFile(arg);
            cm.checkWorkFile();
        }catch (NoSuchElementException e) {
            SoundPlayer.playSound("Pud_ability_hook_miss_01_ru.wav",2500);
            System.out.println("Они убили Кени");
            result = false;}

        Scanner scan = new Scanner(System.in);
        while (result){
            String commandWithOutArgs ="";
            String args="";
            try {
                String[] commandLine = cm.separator();
                commandWithOutArgs = commandLine[0];
                args = "";
                if (commandLine.length > 1) {
                    args = commandLine[1];
                }}catch (NoSuchElementException e) {
                SoundPlayer.playSound("Pud_ability_hook_miss_01_ru.wav",2500);
                System.out.println("Они убили Кени");
                result = false;

            }
            try {
                if ((commands.get(commandWithOutArgs.toUpperCase()) == null) | (commandWithOutArgs.equals(""))) {
                    throw new NoSuchCommandException();
                }
                result = commands.get(commandWithOutArgs.toUpperCase()).execute(args);
            } catch (NoSuchCommandException | IllegalKeyException e) {
                System.out.println(e.getMessage());
            }catch (NumberFormatException e){
                System.out.println("String там где не надо");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Слишком много пробелов попробуйте");
            }catch (NoSuchElementException e) {
            SoundPlayer.playSound("coins.wav",1000);
            System.out.println("Они убили Кени");
            result = false;

        }

        }

    }


}*/
