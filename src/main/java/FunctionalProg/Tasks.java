package FunctionalProg;

import java.util.*;
import java.util.stream.Collectors;

public class Tasks {
    public static void main(String[] args) {
        List <Integer> integers = Arrays.asList(1, 2, 3, 4, 8, 20, 5, 25, 15, 10);
        OptionalDouble average = integers.stream()
                .filter(value -> value % 2 != 0 && value % 5 == 0)
                .mapToInt(Integer::intValue)
                .average();
        average.ifPresent(System.out::println);
        task5();
    }

    //дан список строк, найти кол-во уникальных строк длиной более 8 символов.
    public static void task2() {
        List <String> list = Arrays.asList(
                "string-1",
                "string-2",
                "string-3",
                "string-4",
                "string-10",
                "string-10",
                "string-15",
                "string-16",
                "string-16",
                "string-17"
                );
        //var1
        int size = list.stream()
                .filter(s -> s.length() > 8)
                .collect(Collectors.toSet())
                .size();
        System.out.println(size);
        //var2
        long size1 = list.stream()
                .filter(s -> s.length() > 8)
                .distinct() //remove all duplicates
                .count();
        System.out.println(size1);

    }
    //дана Map<String, Integer> . Найти суму всех значений, длина ключей которых меньше 7 символов
    public static void task3(){
        Map <String, Integer> map = new HashMap<String, Integer>(){{
                    put("string1", 1);
                    put("string10", 2);
                    put("string12", 3);
                    put("string2", 4);
                    put("string3", 5);
                    put("str2", 6);
        }};
        int sum = map.entrySet().stream()
                .filter(entery -> entery.getKey().length() < 7)
                .mapToInt(entery -> entery.getValue().intValue())
                .sum();
        System.out.println(sum);

    }

    //Дан список целых чисел, вывести строку, представляющую собой конкотенацию строковых представлений
    //этих чисел. Пример: список {5, 2, 4, 2, 1} результат "52421"
    public static void task4 (){
        List <Integer> intList = Arrays.asList(5, 2, 4, 2, 1);
        String rezultStr = intList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println(rezultStr);
    }

    //дан класс Person с полями firstName, LastName, age
    //вывести полное имя самого старшего человека у которого длинна этого имени не превышает 15 символов.
    public static void task5(){
        List<Person> listPerson = Arrays.asList(
                new Person("Anna", "Pindyurina", 37),
                new Person("Vasya", "Pupkin", 22),
                new Person("Kate", "Ivanova", 22),
                new Person("Petr", "Petrov", 54),
                new Person("Nikolay", "Kolkov", 34),
                new Person("Mila", "Yourchenko", 12)
        );
        Optional<String> maxAge = listPerson.stream()
                .filter(person -> person.getFullName().length() < 15)
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getFullName);
        System.out.println(maxAge);

        Map<Integer, List<Person>> map = listPerson.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(map);
    }


}
class Person{
    String firstName;
    String lastName;
    int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}



