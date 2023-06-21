package Command.CollectionManager;

import Command.CommandList.Insert.Insert;
import Command.CommandList.Save.Save;
import Command.CommandList.UpdateById.UpdateById;
import Command.CommandProcessor.Command;
import Command.Parse.Filler;
import Command.Parse.FlatJsonConverter;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;
import PossibleClassInCollection.Flat.*;


import java.io.*;
import java.nio.file.AccessDeniedException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class CollectionManager implements Serializable{

    private Map<String, Command> commands;
    private String allCommand = "";

    public CollectionManager(Map<String, Command> commands) {
        this.commands = commands;
    }

    private final Date dateOfInitialization = new Date();
    private File workFile = new File("");
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
       /* while (iterator.hasNext()) {
            Map.Entry<Long, Flat> entry = iterator.next();
            if (entry.getKey() == randomKey) {
                randomKey = keyRandom();
            }
        }*/
        return randomKey;
    }
    public boolean chekKey(long key){
        if (flats.containsKey(key)){
            return false;
        }
        return true;
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
                        if(chekKey(flatsArray.getId())){
                        if (fil.cheker(flatsArray, flats)) {
                            flats.put(flatsArray.getId(), flatsArray);
                        }}
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

    public String show() {
       /* for (Integer key: flats.keys()) {
            System.out.println(key+"="+flats.get(key));
        }*/
        String str="";
        if (flats.size()==0){
            return "показывать нечего";
        }
        for (Iterator<Long> it = flats.keySet().iterator(); it.hasNext(); ) {
            long key = it.next();
            str+=(key + " " + flats.get(key)+"\n");
        }
        return str=str.substring(0,str.length()-1)+"конец Show";
    }

    public String help() {
        String str ="<<<<<<<>>>>>>>>>\n";
        for (Command cmd : commands.values()) str+=(cmd.getName() + ": " + cmd.getDescription()+"\n");
    return str=str.substring(0,str.length()-1)+"\n<<<<<<<>>>>>>>>>";
    }

    public String exit() throws NoSuchCommandException, IllegalValueException, IllegalKeyException {
//        System.out.println("Bye");
//        boolean result = false;
        Save save = new Save(this);
        save.execute("");
        return "Bye";
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
        return "Упешно добавлено";

    }
    /*public String insert(long key, BufferedReader reader) throws IOException,NoSuchElementException {
        Flat flatadd;
        if (flats.containsKey(key)){
            return "уже есть такая квартира";
        }else {
            flatadd = scriptFill(reader,key);
            if (flatadd==null){
                return "ошибка в заполнение обьекта flat";
            }else {flats.put(key,flatadd);
            return "успешно добавленно";
            }
        }
    }*/

    /*public Flat scriptFill(BufferedReader reader,Long idWh) throws IOException, NumberFormatException {
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
        *//*try {
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
        }*//*

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
    }*/


    /*public String update(UpdateById update,long idWh) throws IllegalValueException,NoSuchElementException{
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        Flat flat = update.getFlat();

        try {
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (idWh == entry.getValue().getId()) {
                    flats.put(entry.getKey(), flat);
                    return "успешно обнавленно";
                }
            }
            throw new IllegalKeyException("нет такого id");
        } catch (IllegalKeyException e) {
            return (e.getMessage());
        }
    }*/

    public String update(UpdateById update, long idWh) throws IllegalValueException, NoSuchElementException, IllegalKeyException {
        Optional<Map.Entry<Long, Flat>> optionalEntry = flats.entrySet()
                .stream()
                .filter(entry -> idWh == entry.getValue().getId())
                .findFirst();

        return optionalEntry.map(entry -> {
            flats.put(entry.getKey(), update.getFlat());
            return "успешно обновлено";
        }).orElseThrow(() -> new IllegalKeyException("нет такого id"));

    }

   /* public String update(Long idwh, BufferedReader reader) throws IOException, NumberFormatException,NoSuchElementException {
        long id = idwh;
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (id == entry.getValue().getId()) {
                    flats.put(entry.getKey(),scriptFill(reader,idwh));
                    return "";
                }
            }
            throw new IllegalKeyException("нет такого id");
        } catch (IllegalKeyException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }*/
   /*public String update(Long idwh, BufferedReader reader) throws IOException, NumberFormatException, NoSuchElementException, IllegalKeyException {
       Optional<Map.Entry<Long, Flat>> entryOptional = flats.entrySet()
               .stream()
               .filter(entry -> idwh.equals(entry.getValue().getId()))
               .findFirst();

       if (entryOptional.isPresent()) {
           Map.Entry<Long, Flat> entry = entryOptional.get();
           flats.put(entry.getKey(), scriptFill(reader, idwh));
           return "";
       } else {
           throw new IllegalKeyException("нет такого id");
       }
   }*/

    /*public String removeAnyByNumberOfRooms(int rooms) {
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (rooms == entry.getValue().getNumberOfRooms()) {
                    flats.remove(entry.getKey());
                    return ("была удаленна последня добавленная квартира с количеством комнат == " + rooms);
                }
            }
            return ("нет квартир с таким количеством квартир");
        }*/
    public String removeAnyByNumberOfRooms(int rooms) {
        Optional<Map.Entry<Long, Flat>> entryOptional = flats.entrySet()
                .stream()
                .filter(entry -> rooms == entry.getValue().getNumberOfRooms())
                .findFirst();

        if (entryOptional.isPresent()) {
            Map.Entry<Long, Flat> entry = entryOptional.get();
            flats.remove(entry.getKey());
            return "была удалена последняя добавленная квартира с количеством комнат == " + rooms;
        } else {
            return "нет квартир с таким количеством комнат";
        }
    }


    public String save(String filepath) {

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
            return("FileNotFound");
        } catch (AccessDeniedException e) {
            return("отказано в доступе попробуйте другой файл");
        } catch (IOException e) {
        }
        return "сохранено в "+filepath;
    }

    public String save(File file) {
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
            return("отказано в доступе попробуйте другой файл");
        } catch (IOException e) {
        }

        return "сохранено в "+file;
    }

    public String save() {
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
            return("отказано в доступе попробуйте другой файл");
        } catch (IOException e) {
        }
        return "сохранено";
    }

    /*public String countLessThanNumberOfRooms(int rooms) {
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
            return  ("количество квартир с количеством комнат меньше " + rooms + ", равно " + count);
        } catch (IllegalKeyException e) {
            return (e.getMessage());
        }
    }*/
    public String countLessThanNumberOfRooms(int rooms) {
            long count = flats.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().getNumberOfRooms() < rooms)
                    .count();

            if (count == 0) {
                return "количество квартир с комнатами меньше " + rooms + ", равно 0";
            } else {
                return "количество квартир с количеством комнат меньше " + rooms + ", равно " + count;
            }
    }

    public String removeKey(long key) {
        if (flats.get(key)==null){
            return "нет такого ключа";
        }
        flats.remove(key);
        return "успешно удаленно";
    }



    public long getMaxId() {
        if (flats.size() > 0) {
            return flats.values().stream().max(Comparator.comparing(Flat::getId)).get().getId();
        } else {
            return 0;
        }
    }

    /*public String removeGreater(Flat flat) throws IllegalValueException {
        *//*Filler pr = new Filler();
        Flat newflat = pr.parser(getMaxId());*//*
        Flat newFlat = flat;
        int flatssize = flats.size();
        String str ="";
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (flats.size()!=0) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (0 > newFlat.compareTo(entry.getValue())) {
                    iterator.remove();
                    str +=(entry.getKey()+ " удаленно"+"\n");
                }
            }
            if (flatssize == flats.size()) {
                throw new IllegalKeyException("нет квартир лучше");
            }
            return str.substring(0,str.length()-1);
        } catch (IllegalKeyException e) {
            return(e.getMessage());
        }

    }
*/


    public String removeGreater(Flat flat) throws IllegalValueException {
        int flatssize = flats.size();
        try {

        List<Long> removedKeys = flats.entrySet().stream()
                .filter(entry -> flat.compareTo(entry.getValue()) < 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        removedKeys.forEach(key -> flats.remove(key));

        if (flats.size() == flatssize) {
            throw new IllegalKeyException("нет квартир лучше");
        }

        return removedKeys.stream()
                .map(key -> key + " удаленно")
                .collect(Collectors.joining("\n"));}
        catch (IllegalKeyException e){
            return(e.getMessage());
        }
    }


    /*public String removeGreater(BufferedReader reader) throws IllegalValueException, IOException {
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
return "";
    }*/

    /*public String removeLower(Flat flat) throws IllegalValueException {
//        Filler pr = new Filler();
//        Flat newflat = pr.parser(getMaxId());
        Flat newFlat = flat;
        int flatssize = flats.size();
        String str ="";
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        try {
            while (flats.size()!=0) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (0 < newFlat.compareTo(entry.getValue())) {
                    iterator.remove();
                    str +=(entry.getKey()+ " удаленно"+"\n");
                }
            }
            if (flatssize == flats.size()) {
                throw new IllegalKeyException("нет квартир лучше");
            }
            return str.substring(0,str.length()-1);
        } catch (IllegalKeyException e) {
            return(e.getMessage());
        }

    }*/
    public String removeLower(Flat flat) throws IllegalValueException {
        int flatssize = flats.size();
        String[] str = {""};
        try {
            flats.entrySet().stream()
                    .filter(entry -> flat.compareTo(entry.getValue()) > 0)
                    .map(Map.Entry::getKey)
                    .forEach(key -> {
                        flats.remove(key);
                        str[0] += key + " удаленно" + "\n";
                    });
            if (flatssize == flats.size()) {
                throw new IllegalKeyException("нет квартир лучше");
            }
            return str[0].substring(0, str[0].length() - 1);
        } catch (IllegalKeyException e) {
            return e.getMessage();
        }
    }


    /*public String removeLower(BufferedReader reader) throws IllegalValueException, IOException {
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

        return "";
    }*/

    /*public String sort() {
        Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
        ArrayList<House> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Long, Flat> entry = iterator.next();
            list.add(entry.getValue().getHouse());
        }
        Collections.sort(list);
        return String.valueOf(list);
    }*/

    public String sort() {
        List<House> sortedHouses = flats.values()
                .stream()
                .map(Flat::getHouse)
                .sorted()
                .collect(Collectors.toList());

        return String.valueOf(sortedHouses);
    }

    public String clear() {
        flats.clear();
        return "Коллекция отчищена";
    }

    public String info() {
        return ("Collection type: " + flats.getClass().getName() + "\n"
                + "Date of initialization: " + dateOfInitialization + "\n"
                + "Collection size: " + flats.size());
    }

    public String history() {
        String str = allCommand.substring(0,allCommand.length()-1);
        return str;
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
public void historyFiller(String command){
        allCommand+= command+"\n";
}
}
