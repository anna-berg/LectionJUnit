package Serialization;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationRunner {
    public static void main(String[] args) {
        Path path = Paths.get("src","main","java","Serialization", "student.out");

//        writeObject(path);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            Object o = objectInputStream.readObject();
            System.out.println(o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void writeObject(Path path) {
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                                                            new FileOutputStream(path.toFile()))) {
            Person sergey = new Person("Sergey", 23);
            objectOutputStream.writeObject(sergey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Person implements Serializable {
    private static final long serialVersionUID = -8082673711665882019L;
    private String firstName;
    private transient int age; //trandient -что бы это поле не сериализовалось


    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
