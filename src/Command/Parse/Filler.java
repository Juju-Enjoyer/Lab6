package Command.Parse;

import Exceptions.IllegalValueException;
import PossibleClassInCollection.Flat.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.*;

public class Filler implements Serializable {
    /*private Hashtable<Integer, Flat> flats;

    public Filler(Hashtable<Integer, Flat> flats) {
        this.flats = flats;
    }*/

    public Flat parser(long idQWE) throws IllegalValueException, NoSuchElementException  {


    long id = idQWE;
    String name = getInputName();
    Coordinates coo = new Coordinates(getInputX(), getInputY());
    ZonedDateTime date = ZonedDateTime.now();
    long area = getArea();
    int rooms = getNumberOfRooms();
    Furnish furnish = Furnish.parse();
    View view = View.parse();
    Transport transport = Transport.parse();
    House hou = new House(getInputName(), getInputYear(), getInputNumberOfFloors(), getInputNumberOfFlatsOnFloor(), getInputNumberOfLifts());

    return new Flat(id, name, coo, date, area, rooms, furnish, view, transport, hou);

    }

    public long getLong() throws NoSuchElementException{
        Scanner scLong = new Scanner(System.in);
        boolean flag = false;
        long example = 0L;
        String str = "";
        while (!flag) {
            try {
                str = scLong.nextLine();
                if (!(str.isEmpty())) {
                    example = Long.valueOf(str);
                    if (example > 0) {
                        flag = true;
                    } else {
                        throw new IllegalValueException("больше 0");
                    }
                } else {
                    throw new IllegalValueException("не пустой");
                }
            } catch (IllegalValueException e) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.println("long vedi");
            }catch (ArithmeticException e){
                System.out.println("Число не входит в диапазон типа");

        }


        }
        return example;
    }
    public int getInt() throws NoSuchElementException{
        Scanner scLong = new Scanner(System.in);
        boolean flag = false;
        int example = 0;
        String str = "";
        while (!flag) {
            try {
                str = scLong.nextLine();
                if (!(str.isEmpty())) {
                    example = Integer.valueOf(str);
                    if (example > 0) {
                        flag = true;
                    } else {
                        throw new IllegalValueException("больше 0");
                    }
                } else {
                    throw new IllegalValueException("не пустой");
                }
            } catch (IllegalValueException e) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.println("число надо");
            }catch (ArithmeticException e){
                System.out.println("Число не входит в диапазон типа");


        }

        }
        return example;
    }



    public String getInputName() throws IllegalValueException,NoSuchElementException {
       System.out.println("name:String");
        Scanner scName = new Scanner(System.in);
        String name;
        do {name="";

            try { name = scName.nextLine();
                if (name.isEmpty()) {
                    throw new IllegalValueException("не пустой");
                }
            } catch (IllegalValueException e) {
                System.out.println(e.getMessage());
            }

            }


        while (name.isEmpty());
        return '"'+name+'"';
       }


    public long getInputX() throws NoSuchElementException{
        System.out.println("coordinates x:long");
        Scanner scX = new Scanner(System.in);
        boolean flag = false;
        long x = 0;
        String str = "";
        while (flag == false) {
            try {
                str = scX.nextLine();
                if (!(str.isEmpty())) {
                    x = Long.valueOf(str);
                    flag = true;
                }
                else if (str.isEmpty()) {
                    x=0;
                    flag=true;}
            }catch (NumberFormatException e){
                System.out.println("число надо");}
            catch (ArithmeticException e){
                System.out.println("Число не входит в диапазон типа");
            }
        }

        return x;
    }

    public Integer getInputY() throws NoSuchElementException{
        System.out.println("coordinates y:int > -470");
        Scanner scY = new Scanner(System.in);
        boolean flag = false;
        Integer y = 0;
        String str = "";
        while (flag == false) {
            try {
                str = scY.nextLine();
                if (!(str.isEmpty())) {
                    y = Integer.valueOf(str);
                    if ((y > -470)) {
                        flag = true;
                    } else if (y <= -470) {
                        throw new IllegalValueException("больше -470");
                    }
                } else {
                    throw new IllegalValueException("не пустой");
                }
            } catch (IllegalValueException e) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.println("число надо");
            }catch (ArithmeticException e){
                System.out.println("Число не входит в диапазон типа");
            }


        }
        return y;
    }


    public int getInputYear() throws NoSuchElementException{
        System.out.println("year:int >0, <431");
        Scanner scYear = new Scanner(System.in);
        boolean flag = false;
        int example = 0;
        String str = "";
        while (!flag) {
            try {
                str = scYear.nextLine();
                if (!(str.isEmpty())) {
                    example = Integer.valueOf(str);
                    if (example > 0 && example < 431) {
                        flag = true;
                    } else if (example < 0 || example > 431) {
                        throw new IllegalValueException("меньше 431 и больше 0");
                    }
                } else {
                    throw new IllegalValueException("не пустой");
                }
            } catch (IllegalValueException e ) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.println("long vedi");
            }
            catch (ArithmeticException e){
                System.out.println("Число не входит в диапазон типа");
            }

        }
        return example;
    }

    public int getInputNumberOfFloors() {
        System.out.println("NumberOfFloors:int");
        return getInt();
    }

    public Integer getInputNumberOfFlatsOnFloor() {
        System.out.println("NumberOfFlatsOnFloor:int");
        return getInt();
    }

    public int getInputNumberOfLifts() {
        System.out.println("NumberOfLifts:int");
        return getInt();
    }
    public long getArea(){
        System.out.println("Area:long");
        return getLong();
    }
    public int getNumberOfRooms() throws NoSuchElementException{
        System.out.println("NumberOfRooms:int");
        Scanner scLong = new Scanner(System.in);
        boolean flag = false;
        int example = 0;
        String str = "";
        while (!flag) {
            try {
                str = scLong.nextLine();
                if (!(str.isEmpty())) {
                    example = Integer.valueOf(str);
                    if (example > 0) {
                        flag = true;
                    } else {
                        throw new IllegalValueException("больше 0");
                    }
                } else {
                    throw new IllegalValueException("не пустой");
                }
            } catch (IllegalValueException e) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.println("int vedi");
            }
            catch (ArithmeticException e){
                System.out.println("Число не входит в диапазон типа");
            }

        }
        return example;
    }
    public boolean cheker(Flat flat, Hashtable<Long, Flat> flats){
        try {


            Iterator<Map.Entry<Long, Flat>> iterator = flats.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, Flat> entry = iterator.next();
                if (entry.getValue().getId() == flat.getId()) {
                    return false;
                }
            }
            if ((flat.getName() == null) || (flat.getName().equals(""))) {
                return false;
            }
            if (flat.getCoordinates() == null) {
                return false;
            }
            if ((flat.getCoordinates().getY() == null) || (flat.getCoordinates().getY() <= -470)) {
                return false;
            }
            if (flat.getCreationDate() == null) {
                return false;
            }
            if ((String.valueOf(flat.getArea()) == null) || (flat.getArea() <= 0)) {
                return false;
            }
            if ((String.valueOf(flat.getNumberOfRooms()) == null) || (flat.getNumberOfRooms() <= 0)) {
                return false;
            }
            if (!Furnish.cheker(flat.getFurnish())) {
                return false;
            }
            if (!Transport.cheker(flat.getTransport())) {
                return false;
            }
            if (!View.cheker(flat.getView())) {
                return false;
            }
            if (flat.getHouse() == null) {
                return false;
            }
            if ((flat.getHouse().getName() == null) || (flat.getHouse().getName().equals(""))) {
                return false;
            }
            if ((String.valueOf(flat.getHouse().getYear()) == null) || ((flat.getHouse().getYear() > 431) || (flat.getHouse().getYear() <= 0))) {
                return false;
            }
            if ((flat.getHouse().getNumberOfFloors() <= 0) || (String.valueOf(flat.getHouse().getNumberOfFloors()) == null)) {
                return false;
            }
            if ((flat.getHouse().getNumberOfFlatsOnFloor() <= 0) || (String.valueOf(flat.getHouse().getNumberOfFlatsOnFloor()) == null)) {
                return false;
            }
            if ((flat.getHouse().getNumberOfLifts() <= 0) || (String.valueOf(flat.getHouse().getNumberOfLifts()) == null)) {
                return false;
            }
        }catch (ArithmeticException e){
            return false;
        }

        return true;
    }


}
