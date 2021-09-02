package StringsTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringsApp {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        letterCounter(s);
        treemSpaces(s);
        System.out.println("Contains " + worldCounter(s) + " words.");
        System.out.print("Contains this numbers: ");
        for (int i: filterNumbers(s)) {
            System.out.print(" " + i);
        }

        System.out.println(repeatedSymbols(s));
    }
public static void letterCounter(String s){
    int conutLowerCase = 0;
    int conutApperCase = 0;

    String line = s;
    char [] arr = line.toCharArray();
    for (char ch: arr) {
        if (Character.isAlphabetic(ch)) {            //   s.matches("^[a-zA-Z][a-zA-Z\\s]")) {
            if (Character.isLowerCase(ch)) {
                conutLowerCase++;
            } else {
                conutApperCase++;
            }
        }
    }
    System.out.println("The line \""+ s + "\" have " + conutApperCase + " letter Upper Case and " +
            conutLowerCase + " letter Lower Case.");
}

public static String treemSpaces(String s){
    return s.replaceAll(" +", " ").trim();
}

public static int worldCounter(String s){
    return treemSpaces(s).split(" ").length;
}

public static int[] filterNumbers(String s){
    String[] arr = s.split("\\D+");
    List <Integer> listInt = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
        if ( !(arr[i].equals(""))){
            listInt.add(Integer.parseInt(arr[i]));
        }
    }
    int [] arrInt = new int[listInt.size()];
    for (int i = 0; i < listInt.size(); i++) {
        arrInt[i] = listInt.get(i);
    }
    return arrInt;
}
public static String repeatedSymbols(String input){
    return new StringBuilder(
            new StringBuilder(input)
                    .reverse()
                    .toString()
                    .replaceAll("(.)(?=.*\\1)", ""))
                    .reverse()
                    .toString();
}
}
