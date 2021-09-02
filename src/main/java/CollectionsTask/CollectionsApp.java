package CollectionsTask;

import javafx.util.Pair;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Arrays.asList;

public class CollectionsApp {
    static List<File> fileList = new ArrayList<File>();
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //task number 1
        firstReadFromFileAndWriteReverce();
        System.out.println("First task done!");

        //task number 2
        System.out.print("Enter the positive number:");
        twoStack();

        //task number 3
        System.out.println("path to deep: (my path /home/anuta/Java)");
        File file = new File("/home/anuta/Java");
        fileList = threeFileList(file);
        fileList.forEach(System.out::println);

        //task number 4
        System.out.println("Task number 4, the rezult for list 1, -6, 8, -9, 17, -1, 2, 7 is:");
        List<Integer> negativePositiveNumbers = new ArrayList<Integer>(
                asList(1, -6, 8, -9, 17, -1, 2, 7));
        fourListSort(negativePositiveNumbers);

        //task number 5
        System.out.println("Task number 5, the rezult for list (9, 2, 7, 10, 11, 6, 3, 8, 1, 4) and x=5:");
        List<Integer> theNumbers = new ArrayList<Integer>(asList(9, 2, 7, 10, 11, 6, 3, 8, 1, 4));
        fiveSortingByIndexX(theNumbers, 5);

        //task number 6
        System.out.println("Task number 6");
        System.out.println("The rezult for cheking line ({[]}):" + six("({[]})"));
        System.out.println(six("The rezult for cheking line {[}):" + "({[})"));

        //task number 7
        System.out.println("Task number 7, Words and their repeats frim file text.txt: ");
        seven();

        //task number 8
        System.out.println("Task number 8, min delta for 99 in list (5, 88, 98, 2, 1, 45, 74, 65, 32, 14) is:");
        Eight eight = new Eight();
        eight.setSet(new TreeSet<Integer>(Arrays.asList(5, 88, 98, 2, 1, 45, 74, 65, 32, 14)));
        System.out.println(eight.minDelta(99));
    }

    public static void firstReadFromFileAndWriteReverce(){
        String line = null;
        List <String> listStr = new ArrayList<String>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.dat"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("out.txt"));
        ) {
            while ((line = bufferedReader.readLine()) != null){
                listStr.add(line);
            }
            Collections.reverse(listStr);
            for (String s: listStr) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    /*   try (FileInputStream fileInputStream = new FileInputStream("input.dat");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    /*    try  {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("input.dat"));
        List<String> listStr = new ArrayList<String>();
        listStr = (List<String>) objectInputStream.readObject();
        System.out.println(listStr);
    } catch (ClassNotFoundException | IOException e) {
        e.printStackTrace();
    }*/
    }
    public static void twoStack(){
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        int number = scanner.nextInt();
        scanner.close();
        while (number > 0){
            stack.push(number%10);
            number = number/10;
        }
        stack.forEach(System.out::println);
    }
    public static List<File> threeFileList (File file){
        File [] currentFolder = file.listFiles();
        for (int i = 0; i < currentFolder.length; i++) {
            if (currentFolder[i].isDirectory()){
                fileList.add(currentFolder[i]);
                threeFileList(currentFolder[i]);
            }
        }
    return fileList;
    }
    public static void fourListSort(List<Integer> negativePositiveNumbers){
//        Collections.sort(negativePositiveNumbers, Collections.reverseOrder());
        int listSize = negativePositiveNumbers.size()-1;
        for (int i = 0; i < negativePositiveNumbers.size()-1; i++) {
            if (negativePositiveNumbers.get(i) < 0){
                negativePositiveNumbers.add(negativePositiveNumbers.get(i));
                negativePositiveNumbers.remove(negativePositiveNumbers.get(i));
            }
        }
        negativePositiveNumbers.forEach(System.out::println);
    }
    public static void fiveSortingByIndexX(List<Integer> list, int x){
        int size = list.size();

        int lessThanXIndex = 0;
        int greaterThanIndex = list.size() - 1;
        for (Object item: list.toArray()) {
            if ((Integer) item < x) {
                list.set(lessThanXIndex, (Integer) item);
                lessThanXIndex++;
            } else {
                list.set(greaterThanIndex, (Integer) item);
                greaterThanIndex--;
            }
        }
        list.forEach(System.out::println);

    }
    public static boolean six (String line){
        Map<Character, Character> bPattern = new HashMap<Character, Character>();
        boolean rezult = false;
        bPattern.put('[', ']');
        bPattern.put('{', '}');
        bPattern.put('(', ')');

/*        bPattern.put(']', '[');
        bPattern.put('}', '{');
        bPattern.put(')', '(');*/

        Stack <Character> stackOpen = new Stack<Character>();
        Stack <Character> stackClose = new Stack<Character>();
//        for (char c : line.toCharArray()) {
//            if (bPattern.containsValue(c)) {
//                stack.push(c);
//                rezult = false;
//            } else {
//                if (bPattern.containsKey(c)) {
//                    System.out.println(bPattern.get(c));
//                    char ch = stack.peek();
//                    System.out.println(ch);
//                    if (stack.isEmpty() || !bPattern.get(c).equals(ch)) {
//                        rezult = false;
//                    } else {
//                        if (bPattern.get(c).equals(ch)){
//                            stack.pop();
//                            rezult = true;
//                        }
//                    }
//                }
//            }
//        }
        for (char c : line.toCharArray()) {
            if (bPattern.containsKey(c)) {
                stackOpen.push(c);
                stackClose.push(bPattern.get(c));
            } else {
                if (bPattern.containsValue(c)) {
                    if (stackOpen.isEmpty()){
                        rezult = false;
                    } else {
                        if (stackClose.pop().equals(c)){
                            stackOpen.pop();
                            rezult = true;
                        }
                    }
                    }
                }
            if (!stackOpen.isEmpty()){
                rezult = false;
            }
        }
        return rezult;
    }

    public static void seven(){
        Map <String, Integer> map = new HashMap<>();
        List <String> listOfAll = new ArrayList<String>();
        Set <String> set = new HashSet<String>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("text.txt"))){
            while ((line = bufferedReader.readLine()) != null){
                Collections.addAll(listOfAll, line.split("\\s"));
                Collections.addAll(set, line.split("\\s"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s: set) {
            int i = Collections.frequency(listOfAll, s);
            map.put(s, i);
        }
        for (Map.Entry<String, Integer> pair: map.entrySet()) {
            String key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println(key + ": " + value);
        }
    }


}

class Eight {
    private Set <Integer> set;

    public Set<Integer> getSet() {
        return set;
    }
    public void setSet(Set<Integer> set) {
        this.set = set;
    }

    void start(){
            System.out.println("Basic numbers:");
            for (int i: set) {
                System.out.print(i + " ");
            }
        }

    public void add(int intToAdd){
        set.add(intToAdd);
    }

    public void delete(int intToRemove){
        set.remove(intToRemove);
    }

    public int minDelta(int x){
        int min = Integer.MAX_VALUE;
        int minValue = 0;
        if (this.set.isEmpty()){
            throw new NullPointerException("Set is empty!");
        }
        for (int i: set) {
            Integer tmp = i - x;
            tmp = Math.abs(tmp);
            if (tmp < min){
                min = tmp;
                minValue = i;
            }
        }
        return minValue;
    }
}
