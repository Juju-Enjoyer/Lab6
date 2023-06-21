package Command.CommandList.ExecuteScript;

import Command.CollectionManager.CollectionManager;
import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;
import PossibleClassInCollection.Flat.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.ZonedDateTime;

public class ExecuteScriptNewEra implements Command, Serializable {
    String argument;
    CollectionManager cm;
    public ExecuteScriptNewEra(){}
    public ExecuteScriptNewEra(CollectionManager cm){
        this.cm=cm;
    }
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
            this.argument = argument;
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
    public Flat scriptFill(BufferedReader reader, Long idWh) throws IOException, NumberFormatException {
        long id = idWh;
        String name = "";
        long cooX = 0;
        Integer cooY = 0;
        ZonedDateTime creationDate = ZonedDateTime.now();
        long area = 0;
        int numberOfRooms = 0;
        Furnish furnish = null;
        View view = null;
        Transport transport = null;
        String nameHouse = "";
        int year = 0;
        int numberOfFloors = 0;
        Integer numberOfFlatsOnFloor = 0;
        int numberOfLifts = 0;
        int error = 0;
        BufferedReader read = reader;
        /*try {
            key = Long.parseLong(arg);
            if (getCollection().containsKey(Long.valueOf(arg))) {
                throw new IllegalKeyException("fwf");
            }
        } catch (NumberFormatException e) {
            System.out.println("хуяню сказал");
            error+=1;
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
            error+=1;
        }*/

        try {
            name = read.readLine();
            if ((name == null) || (name.equals(""))) {
                throw new IllegalValueException("Не правильно введено имя");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        }
        try {
            cooX = Long.parseLong(read.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Координата x введена не верна");
            error += 1;
        }
        try {
            cooY = Integer.parseInt(read.readLine());
            if ((cooY == null) || (cooY < -470)) {
                throw new IllegalValueException("Координата y введена не верна");
            }
        } catch (NumberFormatException e) {
            System.out.println("String там где не надо");
            error += 1;
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        }
        creationDate = ZonedDateTime.now();
        try {
            area = Long.parseLong(read.readLine());
            if ((String.valueOf(area) == null) || (area <= 0)) {
                throw new IllegalValueException("Area введено не правильно");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        } catch (NumberFormatException e) {
            System.out.println("String там где не надо");
            error += 1;
        }
        try {
            numberOfRooms = Integer.parseInt(read.readLine());
            if ((String.valueOf(numberOfRooms) == null) || (numberOfRooms <= 0)) {
                throw new IllegalValueException("NunberOfRooms введено не правильно");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        } catch (NumberFormatException e) {
            System.out.println("String там где не надо");
            error += 1;
        }
        try {
            furnish = Furnish.chekerScript(read.readLine());
            if (furnish == null) {
                throw new IllegalValueException("Furnish введено не правильно");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        }
        try {
            view = View.chekerScript(read.readLine());
            if (view == null) {
                throw new IllegalValueException("View введено не верно ");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        }
        try {
            transport = Transport.chekerScript(read.readLine());
            if (transport == null) {
                throw new IllegalValueException("Transport введено не правильно");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        }
        try {
            nameHouse = read.readLine();
            if ((nameHouse == null) || (nameHouse.equals(""))) {
                throw new IllegalValueException("House name введено не правильно");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        }
        try {
            year = Integer.parseInt(read.readLine());
            if ((String.valueOf(year) == null) || (year <= 0) || (year > 431)) {
                throw new IllegalValueException("Area введено не правильно");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        } catch (NumberFormatException e) {
            System.out.println("String там где не надо");
            error += 1;
        }
        try {
            numberOfFloors = Integer.parseInt(read.readLine());
            if ((String.valueOf(numberOfFloors) == null) || (numberOfFloors <= 0)) {
                throw new IllegalValueException("Area введено не правильно");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        } catch (NumberFormatException e) {
            System.out.println("String там где не надо");
            error += 1;
        }
        try {
            numberOfFlatsOnFloor = Integer.parseInt(read.readLine());
            if ((String.valueOf(numberOfFlatsOnFloor) == null) || (numberOfFlatsOnFloor <= 0)) {
                throw new IllegalValueException("Area введено не правильно");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        } catch (NumberFormatException e) {
            System.out.println("String там где не надо");
            error += 1;
        }
        try {
            numberOfLifts = Integer.parseInt(read.readLine());
            if ((String.valueOf(numberOfLifts) == null) || (numberOfLifts <= 0)) {
                throw new IllegalValueException("Area введено не правильно");
            }
        } catch (IllegalValueException e) {
            System.out.println(e.getMessage());
            error += 1;
        } catch (NumberFormatException e) {
            System.out.println("String там где не надо");
            error += 1;
        }

        if (error == 0) {
            return new Flat(id, name, new Coordinates(cooX, cooY), creationDate, area, numberOfRooms, furnish, view, transport, new House(nameHouse, year, numberOfFloors, numberOfFlatsOnFloor, numberOfLifts));
        }
        return null;
    }

    @Override
    public String execute(String args) throws NoSuchCommandException, IllegalKeyException, IllegalValueException {

        return "конец скрипта";
    }
}

