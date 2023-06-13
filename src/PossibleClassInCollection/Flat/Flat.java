package PossibleClassInCollection.Flat;

import java.io.Serializable;
import java.time.ZonedDateTime;


public class Flat implements Comparable<Flat>, Serializable {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate ; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Значение поля должно быть больше 0
    private int numberOfRooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле может быть null
    private View view; //Поле не может быть null
    private Transport transport; //Поле может быть null
    private House house; //Поле не может быть null

    public Flat(long id, String name, Coordinates coordinates, ZonedDateTime creationDate, long area, int numberOfRooms, Furnish furnish, View view, Transport transport, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }
    public void setTransport(Transport transport){
        this.transport=transport;
    }
    public void setView(View view){
        this.view=view;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public long getArea() {
        return area;
    }

    public long getNumberOfRooms() {
        return numberOfRooms;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public View getView() {
        return view;
    }

    public Transport getTransport() {
        return transport;
    }

    public House getHouse() {
        return house;
    }
    public int getEvaluationView(){
        if(this.view==View.GOOD){
            return 4;
        }
        else if(this.view==View.NORMAL){
            return 3;
        }
        else if(this.view==View.YARD){
            return 2;
        }
        else if(this.view==View.BAD){
            return 1;
        }
        else if(this.view==View.TERRIBLE){
            return 0;
        }
        return 0;
    }
    public int getEvaluationTransport(){
        if(this.transport==Transport.NORMAL){
            return 4;
        }
        else if(this.transport==Transport.ENOUGH){
            return 3;
        }
        else if(this.transport==Transport.FEW){
            return 2;
        }
        else if(this.transport==Transport.LITTLE){
            return 1;
        }
        else if(this.transport==Transport.NONE){
            return 0;
        }
        return 0;
    }
    public int getEvaluationFurnish(){
        if(this.furnish==Furnish.DESIGNER){
            return 4;
        }
        else if(this.furnish==Furnish.FINE){
            return 3;
        }
        else if(this.furnish==Furnish.LITTLE){
            return 2;
        }
        else if(this.furnish==Furnish.BAD){
            return 1;
        }
        else if(this.furnish==Furnish.NONE){
            return 0;
        }
        return 0;
    }

    @Override
    public String toString (){
        return "{" + "\n"
                + "id=" + id + "\n"
                + "name=" + name + "\n"
                + "coordinates=" + coordinates +"\n"
                + "creationDate=" + creationDate + "\n"
                + "area=" + area + "\n"
                + "numberOfRooms=" + numberOfRooms + "\n"
                + "furnish=" + furnish + "\n"
                + "view=" + view +"\n"
                + "transport=" + transport +"\n"
                +"house=" + house
                + '}';

    }

    @Override
    public int compareTo(Flat o) {
        int flatLeft=0;
        flatLeft+=Long.compare(this.area,o.area);
        flatLeft+=Long.compare(this.numberOfRooms,o.numberOfRooms);
        flatLeft+=Integer.compare(this.getEvaluationFurnish(),o.getEvaluationFurnish());
        flatLeft+=Integer.compare(this.getEvaluationView(),o.getEvaluationView());
        flatLeft+=Integer.compare(this.getEvaluationTransport(),o.getEvaluationTransport());
        flatLeft+=this.house.compareTo(o.house);
        return Integer.compare(flatLeft, 0);
    }


}

