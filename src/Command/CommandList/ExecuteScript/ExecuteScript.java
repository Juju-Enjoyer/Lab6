/*
package Command.CommandList.ExecuteScript;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Command.Parse.FlatJsonConverter;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ExecuteScript implements Command, Serializable {
    private CollectionManager cm;
    private String argument;
    private ArrayList<String> scripts = new ArrayList<>();
    public ExecuteScript(CollectionManager cm){
        this.cm=cm;
    }
    public ExecuteScript(){}


    @Override
    public String getName() {
        return "EXECUTE_SCRIPT";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    @Override
    public void setArgument(String argument) throws NoSuchCommandException {
this.argument=argument;
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
    public String execute(String args) throws JsonSyntaxException, NoSuchCommandException{
        Map<String,Command> prikol = cm.getCommands();
        Scanner scFile = new Scanner(System.in);
        FlatJsonConverter gson = new FlatJsonConverter(cm.getCollection());
        String result = "";
        String arg = "";
        if (args.isEmpty()){
            throw new NoSuchCommandException();
        }
        else {
            try{
                File file = new File(args);
                if (file.exists()) {
                    while ((!file.canRead())&(!file.canWrite())) {
                        System.out.println("что то не так или не читабельно либо не записываемо");
                        file = new File(scFile.next());
                    }

                    InputStream isr = new FileInputStream(file);
                    InputStreamReader inputStreamReader = new InputStreamReader(isr);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = "not null";
                    if (scripts.contains(file.getName())){
                        result +=" " + "Этот скрипт вызовет рекурсию" +"\n";

                    }
                    else {
                        scripts.add(file.getName());
                    }
                    while ((line!=null)){

                        line = reader.readLine();
                        String command = line.split(" ")[0];
                        if (line.split(" ").length > 1) {
                            arg = line.split(" ")[1];
                        }
                        else if (line.split(" ").length == 1){
                            arg ="";
                        }

                        try {
                            if ( (cm.getCommands().get(command.toUpperCase()) == null) | (command.isEmpty()) ) {
                                throw  new NoSuchCommandException();
                            }
                        }catch (NoSuchCommandException e){
                            continue;}


                        if (prikol.get(command.toUpperCase()).getName() == "INSERT"){
                            try {

                                if (arg.isEmpty()){
                                    throw new NumberFormatException();
                                }
                                else if (cm.getCollection().containsKey(Long.valueOf(arg))){
                                    throw new IllegalKeyException("уже есть квартира с таким номером"+"\n");
                                }
                                result += cm.insert(Long.parseLong(arg),reader)+"\n";
                            }
                            catch (IllegalKeyException e){
                                result+=(e.getMessage());}
                            catch (NumberFormatException e){
                                result+=("String там где не надо");
                            }


                        }
                        else if (prikol.get(command.toUpperCase()).getName() == "UPDATEBYID"){
                            try {
                                long key = Long.parseLong(arg);
                                result +=" " + cm.update(key,reader);
                            }catch (NumberFormatException e){
                                result+=("String там где не надо"+"\n");
                            }

                        }
                        else if (prikol.get(command.toUpperCase()).getName() == "REMOVEGREATER"){
                            try {
                                if (!arg.isEmpty()){
                                    throw new NoSuchCommandException();
                                }
                                result +=" " + cm.removeGreater(reader);
                            }catch (NoSuchCommandException e){
                                System.out.println("String там где не надо");
                            }

                        }
                        else if (prikol.get(command.toUpperCase()).getName() == "REMOVELOWER"){
                            try {
                                if (!arg.isEmpty()){
                                    throw new NoSuchCommandException();
                                }
                                result +=" " +cm.removeLower(reader);
                            }catch (NoSuchCommandException e){
                                System.out.println("String там где не надо");
                            }

                        }
                        else {
                            Command commandExecute = prikol.get(command.toUpperCase());
                            commandExecute.setCm(cm);
                            result += commandExecute.execute(arg)+"\n";}}
                    scripts.clear();
                    reader.close();

                    if (line==null){
                        result += "выполнено"+"\n";
                    }
                }

            } catch (IOException e) {
                result = "";
            }catch (NullPointerException e){}
            catch (IllegalValueException | IllegalKeyException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

}

*/
/* execute_script script1*/
