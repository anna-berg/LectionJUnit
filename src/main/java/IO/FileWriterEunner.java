package IO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileWriterEunner {
    public static void main(String[] args) {
        Path file = Paths.get( "src", "main", "java", "IO", "output2.txt");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file)) {
            Files.write(file, Arrays.asList("Hello world", "I love Java"));
//        File file = new File(String.join(File.separator, "src", "main", "java", "IO", "output.txt"));
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
//            bufferedWriter.append("I love Java");
//            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}