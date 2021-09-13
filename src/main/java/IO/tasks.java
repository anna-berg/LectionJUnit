package IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class tasks {
    public static void main(String[] args) throws IOException {
        task5();
    }

    //Задан файл с текстом, найти и вывести на консоль все слова, начинающиеся с гласных букв.
    public static void task1() {
        List<String> charVowels = Arrays.asList("у", "е", "ы", "а", "о", "э", "ю", "я", "и");
        String vowels = "уеыаоэюяи";
        Path path = Paths.get("src", "main", "java", "IO", "text.txt");
        try (Stream<String> reader = Files.lines(path)) {
            reader
//                    .map(s -> s.split("\\s"))
//                    .filter("", " "::startsWith)
//                    .filter(strings -> strings.startsWith(String.valueOf(charVowels)))
                    .forEach(System.out::println);
//        try(Scanner scanner = new Scanner(path)){
//            while (scanner.hasNext()){
//                String word = scanner.next();
//                char firstSymbol = word.charAt(0);
//                if (vowels.indexOf(firstSymbol) != -1){
//                    System.out.println(word);
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //найти все слова для которых первая буква одного слова совпадает с последней предидущего
    public static void task2() {
        Path path = Paths.get("src", "main", "java", "IO", "text.txt");
        try (Scanner scanner = new Scanner(path)) {
            String prev = null;
            if (scanner.hasNext()){
                prev = scanner.next();
            }
            while (scanner.hasNext()){
                String current = scanner.next();
                if (isLastSymbolEqualsFirstSymbol(prev, current)){
                    System.out.println(prev + " " + current);
                }
                prev = current;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isLastSymbolEqualsFirstSymbol(String prev, String current) {
    return prev.charAt(prev.length()-1) == current.charAt(0);
    }

//  найти наибольшее число цифр, идущих подряд
    public static void task3() throws IOException {
        Path path = Paths.get("src", "main", "java", "IO", "text.txt");
        Files.readAllLines(path).stream()
                .map(tasks::findMaxMunbers)
                .filter(integer -> integer>0)
                .forEach(System.out::println);
    }

    private static Integer findMaxMunbers(String line) {
        Integer rezult = 0;
        int count=0;
        for (int i = 0; i < line.length(); i++) {
            if(Character.isDigit(line.charAt(i))){
                count++;
            } else {
                if (rezult<count){
                    rezult = count;
                }
                count = 0;
            }
        }

        return rezult;
    }

//  Задан файл с джава-кодом. Прочитать текст программы из файла и все слова public заменить на private
//  результат сохранить в другой джава файл
    public static void task4() throws IOException {
        Path path = Paths.get("src", "main", "java", "IO", "tasks.java");
        String rezultString = Files.readAllLines(path).stream()
                .map(s -> s.replace("public", "private"))
                .collect(Collectors.joining("\n"));

        Path rezultPath = Paths.get("src", "main", "java", "IO", "testsPrivate.java");
        try(BufferedWriter writer = Files.newBufferedWriter(rezultPath)){
            writer.write(rezultString);
        }
    }

//  Задан файл с джава-кодом. Прочитать текст программы из файла и записать в другой файл в
//  обратном порядке символы каждой строки
    public static void task5(){
        Path path = Paths.get("src", "main", "java", "IO", "tasks.java");
        Path rezult = Paths.get("src", "main", "java", "IO", "tasksTest.java");

        try (Stream<String> lines = Files.lines(path);
             final BufferedWriter bufferedWriter = Files.newBufferedWriter(rezult,
                     StandardOpenOption.APPEND, StandardOpenOption.CREATE)){
            lines.map(line -> new StringBuilder(line))
                    .map(StringBuilder::reverse)
                    .forEach(line -> {
                        try {
                            bufferedWriter.write(line.toString());
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
