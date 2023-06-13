package Command.CollectionManager;

import Command.CommandList.Insert.Insert;
import Command.CommandProcessor.Command;
import Command.Parse.Filler;
import Command.Parse.FlatJsonConverter;
import Command.Parse.Reader;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;
import PossibleClassInCollection.Flat.*;


import javax.management.StringValueExp;
import java.io.*;
import java.nio.file.AccessDeniedException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CollectionManager implements Serializable{

    private Map<String, Command> commands;

    public CollectionManager(Map<String, Command> commands) {
        this.commands = commands;
    }

    private final Date dateOfInitialization = new Date();
    private File workFile = new File("");
    private Reader reader;
    /*Gson gson = new Gson();*/
    private Hashtable<Long, Flat> flats = new Hashtable<>();

    public Hashtable<Long, Flat> getCollection() {
        return flats;
    }

    public void getWorkerFile(String arg) throws FileNotFoundException,NoSuchElementException,NoSuchElementException  {

        Scanner scFile = new Scanner(System.in);
        String line = arg;
        while (line.trim().isEmpty()){
            System.out.println("Задайте рабочий файл так в строчку был задан не очень хоороший файл");
            line = scFile.nextLine();
        }
        File file = new File(line);
        if (file.exists()) {
            while ((!file.canRead()) & (!file.canWrite())) {
                System.out.println("мб выбирите другой файл?");
                file = new File(scFile.nextLine());
            }
            this.workFile = file;
        } else {
            FileOutputStream fos = new FileOutputStream(file);
            while ((!file.canRead()) & (!file.canWrite())) {
                System.out.println("что то не так или не читабельно либо не записываемо");
                file = new File(scFile.nextLine());
            }
            this.workFile = file;
        }
    }

    public long keyRandom() {
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        long randomKey = ThreadLocalRandom.current().nextLong(0, 1000);
        while (iterator.hasNext()) {
            Map.Entry<Long, Flat> entry = iterator.next();
            if (entry.getKey() == randomKey) {
                randomKey = keyRandom();
            }
        }
        return randomKey;
    }


    public void checkWorkFile() throws IOException {
        Filler fil = new Filler();
        if (!(workFile.length() == 0)) {
            try {
                FlatJsonConverter gson = new FlatJsonConverter(flats);
                InputStream isr = new FileInputStream(workFile);
                InputStreamReader inputStreamReader = new InputStreamReader(isr);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = "not null";
                while (!(line == null)) {
                    line = reader.readLine();
                    Flat flatsArray = gson.toFlat(line);
                    if (!(flatsArray == null)) {
                        System.out.println(flatsArray);
                        if (fil.cheker(flatsArray, flats)) {
                            flats.put(keyRandom(), flatsArray);
                        }
                    }
                }
            } catch (IOException e) {
            }catch (ArithmeticException e){
                System.out.println("Число не входит в диапазон типа");
            }
        }
    }

    public void add() throws IllegalValueException {
        Filler pr = new Filler();
        long id = getMaxId() + 1;
        flats.put(id, pr.parser(id));
    }

    public void show() {
       /* for (Integer key: flats.keys()) {
            System.out.println(key+"="+flats.get(key));
        }*/
        for (Iterator<Long> it = flats.keySet().iterator(); it.hasNext(); ) {
            long key = it.next();
            System.out.println(key + " " + flats.get(key));
        }
    }

    public String help() {
        System.out.println("Help");
        LinkedList<String> helpMessage = new LinkedList<>();
        for (Command cmd : commands.values()) helpMessage.add(cmd.getName() + ": " + cmd.getDescription());
    return "Список доступных команд";
    }

    public String exit() {
//        System.out.println("Bye");
//        boolean result = false;
        return "Bye";
    }

    public void fullCommadsList(Map<String, Command> commands) {
        this.commands = commands;
        this.reader = new Reader(commands);
    }


    public String insert(Insert insert) throws  NoSuchElementException{
        try {
            if (flats.containsKey(insert.getArgument())){
                return "уже есть квартира с таким номером";
        }
            flats.put(Long.valueOf(insert.getArgument()), insert.getFlat());
        }catch (NullPointerException e){
            System.err.println("Zalupa");
        }
        show();
        return "Упешно добавлено";

    }
    public void insert(long key, BufferedReader reader) throws IOException,NoSuchElementException {
        flats.put(key,scriptFill(reader,getMaxId()+1));
    }

    public Flat scriptFill(BufferedReader reader,Long idWh) throws IOException, NumberFormatException {
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


    public boolean update(long idWh) throws IllegalValueException,NoSuchElementException{
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        Filler pr = new Filler();
        try {
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (idWh == entry.getValue().getId()) {
                    Flat flat = pr.parser(idWh);
                    flats.put(entry.getKey(), flat);
                    return true;
                }
            }
            throw new IllegalKeyException("нет такого id");
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean update(Long idwh, BufferedReader reader) throws IOException, NumberFormatException,NoSuchElementException {
        long id = idwh;
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (id == entry.getValue().getId()) {
                    flats.put(entry.getKey(),scriptFill(reader,idwh));
                    return true;
                }
            }
            throw new IllegalKeyException("нет такого id");
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public void removeAnyByNumberOfRooms(int rooms) {
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (rooms == entry.getValue().getNumberOfRooms()) {
                    flats.remove(entry.getKey());
                    System.out.println("была удаленна последня добавленная квартира с количеством комнат == " + rooms);
                    break;
                }
                throw new IllegalKeyException("нет квартир с таким количеством квартир");
            }
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(String filepath) {

        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            FileOutputStream fos = new FileOutputStream(filepath);
            FlatJsonConverter conv = new FlatJsonConverter(flats);
            String newLine = System.getProperty("line.separator");
            byte[] entr = newLine.getBytes();
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                byte[] saveObj = conv.toJson(entry.getValue()).getBytes();
                fos.write(saveObj);
                fos.write(entr);
            }
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound");
        } catch (AccessDeniedException e) {
            System.out.println("отказано в доступе попробуйте другой файл");
        } catch (IOException e) {
        }
    }

    public void save(File file) {
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            FlatJsonConverter conv = new FlatJsonConverter(flats);
            String newLine = System.getProperty("line.separator");
            byte[] entr = newLine.getBytes();
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                byte[] saveObj = conv.toJson(entry.getValue()).getBytes();
                fos.write(saveObj);
                fos.write(entr);

            }
            fos.close();
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
        }

    }

    public void save() {
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            String file = String.valueOf(workFile);
            FileOutputStream fos = new FileOutputStream(file);
            FlatJsonConverter conv = new FlatJsonConverter(flats);
            String newLine = System.getProperty("line.separator");
            byte[] entr = newLine.getBytes();
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                byte[] saveObj = conv.toJson(entry.getValue()).getBytes();
                fos.write(saveObj);
                fos.write(entr);
            }
            fos.close();
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
        }
    }

    public void countLessThanNumberOfRooms(int rooms) {
        int count = 0;
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (rooms > entry.getValue().getNumberOfRooms()) {
                    count += 1;
                }
            }
            if (count == 0) {
                throw new IllegalKeyException("количество квартир с комнатами меньше " + rooms + ", равно 0");
            }
            System.out.println("количество квартир с количеством комнат меньше " + rooms + ", равно " + count);
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeKey(long key) {
        flats.remove(key);
    }



    public long getMaxId() {
        if (flats.size() > 0) {
            return flats.values().stream().max(Comparator.comparing(Flat::getId)).get().getId();
        } else {
            return 0;
        }
    }

    public void removeGreater() throws IllegalValueException {
        Filler pr = new Filler();
        Flat newflat = pr.parser(getMaxId());
        int flatssize = flats.size();
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (flats.size()!=0) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (0 > newflat.compareTo(entry.getValue())) {
                    iterator.remove();
                    System.out.println(entry.getKey()+ " удаленно");
                }
            }
            if (flatssize == flats.size()) {
                throw new IllegalKeyException("нет квартир лучше");
            }
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
        }

    }




    public void removeGreater(BufferedReader reader) throws IllegalValueException, IOException {
        Flat newflat = scriptFill(reader,1L);
        int flatssize = flats.size();
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (0 > newflat.compareTo(entry.getValue())) {
                    iterator.remove();
                    System.out.println(entry.getKey() + " удаленно");
                }
            }
            if (flatssize == flats.size()) {
                throw new IllegalKeyException("нет квартир лучше");
            }
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeLower() throws IllegalValueException {
        Filler pr = new Filler();
        Flat newflat = pr.parser(getMaxId());
        int flatssize = flats.size();
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (0 < newflat.compareTo(entry.getValue())) {
                    iterator.remove();
                    System.out.println(entry.getKey()+ " удаленно");
                }
            }
            if (flatssize == flats.size()) {
                throw new IllegalKeyException("нет квартир хуже");
            }
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
        }

    }
    public void removeLower(BufferedReader reader) throws IllegalValueException, IOException {
        Flat newflat = scriptFill(reader,1L);
        int flatssize = flats.size();
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (0 < newflat.compareTo(entry.getValue())) {
                    iterator.remove();
                    System.out.println(entry.getKey() + " удаленно");
                }
            }
            if (flatssize == flats.size()) {
                throw new IllegalKeyException("нет квартир лучше");
            }
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
        }

    }

    public void sort() {
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        ArrayList<House> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Long, Flat> entry = iterator.next();
            list.add(entry.getValue().getHouse());
        }
        Collections.sort(list);
        System.out.println(list);


    }

    public void clear() {
        flats.clear();
    }

    public void info() {
        System.out.println("Collection type: " + flats.getClass().getName() + "\n"
                + "Date of initialization: " + dateOfInitialization + "\n"
                + "Collection size: " + flats.size());
    }

    public void history() {
        System.out.println(reader.getAllCommands());
    }

    public String[] separator(String... agrs) throws NoSuchCommandException {
        return reader.separator();
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public boolean idCheker(Long id) {
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        Map.Entry<Long, Flat> entry = iterator.next();
        while (iterator.hasNext()) {
            if (entry.getValue().getId() == id) {
                return false;
            }
        }
        return true;
    }
}
