package PossibleClassInCollection.Flat;

import Exceptions.IllegalKeyException;

import java.util.Scanner;

public enum Furnish implements ParseEnum{
    DESIGNER,
    NONE,
    FINE,
    BAD,
    LITTLE;
    public static Furnish parse() {
        System.out.println("Furnish: DESIGNER,NONE,FINE,BAD,LITTLE");
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            try {
                String value =sc.nextLine();
                if (!value.isEmpty()) {
                    for (Furnish fur : Furnish.values()) {
                        if (value.toUpperCase().equals(String.valueOf(fur))) {
                            flag = true;
                            return fur;
                        }
                    }if (!flag){
                        throw new IllegalKeyException("нет такого Furnish");
                    }

                }else {
                    throw new IllegalKeyException("не null");
                }
            }catch (IllegalKeyException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    public static boolean cheker(Furnish fur){
        if (!(fur == null)){
            for (Furnish index : Furnish.values()) {
                if (String.valueOf(fur).toUpperCase().equals(String.valueOf(index))) {
                    return true;
                }
            }
            return false;
        }
        else {
            return false;
        }
    }
    public static Furnish chekerScript(String fur){
        if (!(fur == null)){
            for (Furnish index : Furnish.values()) {
                if (String.valueOf(fur).toUpperCase().equals(String.valueOf(index))) {
                    return index;
                }
            }
            return null;
        }
        else {
            return null;
        }
    }


}
