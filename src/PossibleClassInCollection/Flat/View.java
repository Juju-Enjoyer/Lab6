package PossibleClassInCollection.Flat;

import Exceptions.IllegalKeyException;

import java.util.Scanner;

public enum View {
    YARD,
    BAD,
    NORMAL,
    GOOD,
    TERRIBLE;

    public static View parse() {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        System.out.println("View: YARD,BAD,NORMAL,GOOD,TERRIBLE");
        while (!flag) {
            try {
                String value = sc.nextLine();
                if (value != null) {
                    for (View view : View.values()) {
                        if (value.toUpperCase().equals(String.valueOf(view))) {
                            flag = true;
                            return view;
                        }
                    }
                    if (!flag) {
                        throw new IllegalKeyException("нет такого View");
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


    public static boolean cheker(View view) {
        if (!(view == null)) {
            for (View index : View.values()) {
                if (String.valueOf(view).toUpperCase().equals(String.valueOf(index))) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
    public static View chekerScript(String view){
        if (!(view == null)){
            for (View index : View.values()) {
                if (String.valueOf(view).toUpperCase().equals(String.valueOf(index))) {
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
