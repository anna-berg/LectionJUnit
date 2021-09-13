package FunctionalProg;

import java.util.Comparator;

public class LambdaExample {
    public static void main(String[] args) {
//        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(25, 100));
    }
 /*   private static class IntegerComporator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        }*/
}
