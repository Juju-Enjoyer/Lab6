package PossibleClassInCollection.Flat;

public class Coordinates {
    private long x;
    private Integer y; //Значение поля должно быть больше -470, Поле не может быть null

    public Coordinates(long x,Integer y) throws NumberFormatException{
        this.x = x;
        this.y=y;
    }
    @Override
    public String toString () {
        return "Coordinates{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }

    public long getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}


