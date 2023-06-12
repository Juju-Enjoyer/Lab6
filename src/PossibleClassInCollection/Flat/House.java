package PossibleClassInCollection.Flat;

public class House implements Comparable<House>{
    private String name; //Поле не может быть null
    private int year; //Максимальное значение поля: 431, Значение поля должно быть больше 0
    private int numberOfFloors; //Значение поля должно быть больше 0
    private Integer numberOfFlatsOnFloor; //Значение поля должно быть больше 0
    private int numberOfLifts; //Значение поля должно быть больше 0
    public House(String name, int year,int numberOfFloors,Integer numberOfFlatsOnFloor,int numberOfLifts){
        this.name=name;
        this.year=year;
        this.numberOfFloors=numberOfFloors;
        this.numberOfFlatsOnFloor=numberOfFlatsOnFloor;
        this.numberOfLifts=numberOfLifts;
        /*Scanner sc = new Scanner(System.in);
        System.out.println("nameHouse");
        name=sc.nextLine();
        this.name=name;
        System.out.println("yearHouse");
        year= sc.nextLong();
        this.year=year;
        System.out.println("nuberOfFloors");
        numberOfFloors= sc.nextLong();
        this.numberOfFloors=numberOfFloors;
        System.out.println("floorsHouse");
        numberOfLifts=sc.nextLong();
        this.numberOfFloors=numberOfFloors;
        System.out.println("lastFloorHouse");
        numberOfFlatsOnFloor=sc.nextLong();
        this.numberOfFlatsOnFloor=numberOfFlatsOnFloor;
        System.out.println("liftHouse");
        numberOfLifts=sc.nextLong();
        this.numberOfLifts=numberOfLifts;*/
    }
    @Override
    public String toString (){
        return "{" +"\n"
                + "name=" + name + "\n"
                + "year=" + year +"\n"
                + "numberOfFloors=" + numberOfFloors + "\n"
                + "numberOfFlatsOnFloor=" + numberOfFlatsOnFloor + "\n"
                + "numberOfLifts=" + numberOfLifts + "\n"
                + '}';
    }

    @Override
    public int compareTo(House o) {
        int excellenceScores=0;
        excellenceScores -= Long.compare(this.year, o.year);
        excellenceScores += Long.compare(this.numberOfFloors, o.numberOfFloors);
        excellenceScores += Long.compare(this.numberOfFlatsOnFloor,o.numberOfFlatsOnFloor);
        excellenceScores += Long.compare(this.numberOfLifts,o.numberOfLifts);
        return Integer.compare(excellenceScores, 0);
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public Integer getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    public int getNumberOfLifts() {
        return numberOfLifts;
    }
}

