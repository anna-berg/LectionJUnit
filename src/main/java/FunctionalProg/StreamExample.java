package FunctionalProg;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("88", "11", "22", "33", "44", "55", "66");
        IntSummaryStatistics intSummaryStatistics = strings.stream()
                .map(s -> s + s)
                .map(Integer::valueOf)
                .filter(v -> v % 2 == 0)
                .sorted()
                .skip(1)
                .limit(2)
                .mapToInt(Integer::intValue) // из обьекта в приметивный тип
//                .mapToObj(value -> Integer.valueOf(value)) - преобразовать из примит. типа в обьект
//                .forEach(System.out::println);
                .summaryStatistics();
        System.out.println(intSummaryStatistics);

        //создание стрима без коллекции, сразу из обьектов
        List<String> collect = Stream.of("88", "11", "22", "33", "44", "55", "66")
                // peek - принимает консьюмер как и форич, но не терпенальный. не прерывает выполнение стрима
                .peek(value -> System.out.println(value))
                .collect(Collectors.toList());//терминальная опер-я, преобр. стрим в любую коллекцию

        IntStream.of (1, 2, 3, 4); //создать стрим из приметивных типов

        IntStream.range(0, 10) //create fori (from 0 to 10) .rangeClosed (до 10 включительно)
                .forEach(System.out::println);

        //endless iterator, where 0 - изначальное значение
        IntStream.iterate(0, operand -> operand + 3)
/*        new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand + 3;
            }*/
                .skip(10)
                .limit(20)
                .forEach(System.out::println);

        Optional<Student> mabyStudent = Stream.of(
                        new Student(18, "Ivan"),
                        new Student(22, "Ivanna"),
                        new Student(25, "Alex"),
                        new Student(34, "Nikol"),
                        new Student(37, "Anna"),
                        new Student(58, "Vova"),
                        new Student(44, "Petr")
                )
//                .parallel() // распаралелить вычесленя на несколько потоков
                .sequential() //слить все потоки в один
//                .filter(student -> student.getAge() > 20)
//                .map(student -> student.getAge())
//                .reduce( (age1, age2) -> Integer.sum(age1, age2))
//                .reduce((age1, age2) -> Math.max(age1, age2))

                //если возраст ст1 > cт2, то мы возвращаем ст1 иначе ст2            то           иначе
                .reduce((student1, student2) -> student1.getAge() > student2.getAge() ? student1 : student2);
//                optional - типа форича, но "если есть"
//                .ifPresent(System.out::println);

        mabyStudent.ifPresent(System.out::println);
        mabyStudent.map(student -> student.getAge())
                .filter(age -> age<25)
                .flatMap(age -> Optional.of(age*2));


/*        for (String s: strings) {
            String value = s+s;
            Integer intValue = Integer.valueOf(value);
            if(intValue%2 == 0){
                System.out.println(intValue);
            }
        }*/
    }

    

}

class Student {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
