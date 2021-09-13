package concurrent.pool;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
 /*       //создает пул из одного единственного потока
        Executors.newSingleThreadExecutor();

        //создает столько потоков, сколько мы передали (тут 5)
        Executors.newFixedThreadPool(5);

        //безграничный, сколько задач отправили столько потоков будет создано
        Executors.newCachedThreadPool();

        //можем выполнять задачи с какой-то задачей или периодичностью
        Executors.newScheduledThreadPool(3 );

        //создает ThreadPool на основании другой реализации ForkJoinPool
        //создает пул такого размера, какого позвонять колличество процессоров
        Executors.newWorkStealingPool();*/
//ThreadPool - структура данных, которая хранит в себе потоки
        test();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.schedule(()->
                System.out.println("ok"), 3L, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        scheduledExecutorService.awaitTermination(1L, TimeUnit.HOURS);

    }

    private static void test() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //future - обьект с результатом выполнения нашей задачи кот. будет в будующем
        Future<Integer> future = threadPool.submit(() -> {
            Thread.sleep(2000L);
            System.out.println("it's collable");
            return 1;
        });
        //реализация интерфейса Futute
        //можем ее использовать для вызова каких-либо задач использую дефолтный пул
//        CompletableFuture.supplyAsync();
        //метод гет - блокирующий. блокирует наш main
        System.out.println("Rezult " + future.get());
        threadPool.shutdown();//ждет завершение всех задач которые мы отправили в пул
        // threadPool.shutdownNow(); //завершает все потоки, и возвр. те задачи которые не успели выполниться
        // указываем время которое мы можем ожидать
        threadPool.awaitTermination(1L, TimeUnit.HOURS);
        System.out.println("main end");
    }
}
