package concurrent.taskDmDev;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Task2 {
    public static void main(String[] args) {
        int[] values = new int[1_000_000];
        Random random = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(300)+1;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        trackTime(() -> fildMax(values));
        trackTime(() -> {
            try {
                return findMaxParalelle(values, executorService);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return Integer.MIN_VALUE;
        });

        executorService.shutdown();
        /*long startTime = System.currentTimeMillis();
        int rezult = fildMax(values);
        System.out.println(rezult + " : " + (System.currentTimeMillis() - startTime));*/
    }

    private static int trackTime(Supplier<Integer> task){
        long startTime = System.currentTimeMillis();
        int rezult = task.get();
        System.out.println(rezult + " : " + (System.currentTimeMillis() - startTime));
        return rezult;
    }
    private static int fildMax(int[] values){
        return IntStream.of(values)
                .max()
                .orElse(Integer.MIN_VALUE);
    }

    private static int findMaxParalelle (int[] values, ExecutorService executorService) throws ExecutionException, InterruptedException {
        return executorService.submit(() -> IntStream.of(values)
                .parallel()
                .max()
                .orElse(Integer.MIN_VALUE))
                .get();
    }
}
