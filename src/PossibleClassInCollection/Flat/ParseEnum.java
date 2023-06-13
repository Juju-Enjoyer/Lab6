package PossibleClassInCollection.Flat;

import java.util.Scanner;

public interface ParseEnum <T extends Enum> {

    static <T> T parse() {
        Scanner sc = new Scanner(System.in);
        String var = sc.nextLine();
        return (T) var ;
    }
}