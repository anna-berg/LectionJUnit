package IO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputRunner {
    public static void main(String[] args) throws FileNotFoundException {
    reader();
    }
public static void reader (){
    //reading by char
    /*File file = new File(String.join(File.separator, "src", "main", "java", "IO", "text.txt"));
    try (BufferedReader fileInputStream = new BufferedReader(new FileReader(file))) {
        String collect = fileInputStream.lines()
                .collect(Collectors.joining("\n"));
        System.out.println(collect);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }*/

    //reading by Files
    Path path = Paths.get("src", "main", "java", "IO", "text.txt");
    try (Stream<String> lines =  Files.lines(path)) {
        lines.forEach(System.out::println);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public static void fileInputStream (){
    //read info from file by bytes
    File file = new File(String.join(File.separator, "src", "main", "java", "IO", "text.txt"));
    try (FileInputStream fileInputStream = new FileInputStream(file)) {
        byte [] bytes = new byte[fileInputStream.available()];
        byte currentByte;
        int counter = 0;
        while ((currentByte = (byte) fileInputStream.read()) != -1){
            bytes[counter++] = currentByte;
        }
        String stringValue = new String(bytes);
        System.out.println(stringValue);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

