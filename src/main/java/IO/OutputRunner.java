package IO;

import java.io.*;

public class OutputRunner {
    public static void main(String[] args) {
        File file = new File(String.join(File.separator, "src", "main", "java", "IO", "tasks.java"));
        try (BufferedOutputStream fileOutputStream = new BufferedOutputStream( new FileOutputStream(file, true))){
            String value = "Hello World";
            fileOutputStream.write(value.getBytes());
            fileOutputStream.write("\n".getBytes()); // or .write(System.linesSparator.getBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
