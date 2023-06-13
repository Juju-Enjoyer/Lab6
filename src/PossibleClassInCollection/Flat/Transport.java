package PossibleClassInCollection.Flat;

import Exceptions.IllegalKeyException;

import java.io.Serializable;
import java.util.Scanner;

public enum Transport implements ParseEnum, Serializable {
    FEW,
    NONE,
    LITTLE,
    NORMAL,
    ENOUGH;

    public static Transport parse() {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        System.out.println("Transport: FEW,NONE,LITTLE,NORMAL,ENOUGH");
        while (!flag) {
            try {
                String value = sc.nextLine();
                if (value != null) {
                    for (Transport tran : Transport.values()) {
                        if (value.toUpperCase().equals(tran.toString())) {
                            flag = true;
                            return tran;
                        }
                    }
                    if (!flag) {
                        throw new IllegalKeyException("нет такого Transport");
                    }
                } else {
                    throw new IllegalKeyException("не null");
                }
            } catch (IllegalKeyException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static boolean cheker(Transport tran) {
        if (!(tran == null)) {
            for (Transport index : Transport.values()) {
                if (String.valueOf(tran).toUpperCase().equals(String.valueOf(index))) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
    public static Transport chekerScript(String tran){
        if (!(tran == null)){
            for (Transport index : Transport.values()) {
                if (String.valueOf(tran).toUpperCase().equals(String.valueOf(index))) {
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

