package concurrent.port3;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Port{
    public static AtomicInteger containersCapacity = new AtomicInteger(200);
    public static AtomicInteger currentContainersCount = new AtomicInteger(20);

    public AtomicInteger getContainersCapacity() {
        return containersCapacity;
    }
    public AtomicInteger getCurrentContainersCount() {
        return currentContainersCount;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Pier> piers = new ArrayBlockingQueue<>(2, true, List.of(
                new Pier(),
                new Pier()
        ));
        List<Thread> ships = Stream.of(
                        new Ship(piers, 3, 2, 25),
                        new Ship(piers, 1, 4, 10),
                        new Ship(piers, 0, 3, 2),
                        new Ship(piers, 4, 0, 3),
                        new Ship(piers, 2, 4, 60),
                        new Ship(piers, 0, 3, 60),
                        new Ship(piers, 3, 0, 60)
                )
                .map(Thread::new)
                .peek(Thread::start)
                .collect(Collectors.toList());

        for (Thread ship : ships) {
            ship.join();
        }
    }
}
