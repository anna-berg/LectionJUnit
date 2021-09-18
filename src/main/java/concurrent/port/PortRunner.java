package concurrent.port;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
* 1. Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров. Число контейнеров,
находящихся в текущий момент в порту и на корабле, должно быть неотрицательным и
превышающим заданную грузоподъемность судна и вместимость порта. В порту работает
несколько причалов. У одного причала может стоять один корабль. Корабль может загружаться у
причала, разгружаться или выполнять оба действия.
* */

public class PortRunner {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Pier> piers = new ArrayBlockingQueue<>(3, true, List.of(
                new Pier(),
                new Pier(),
                new Pier()
        ));

/*
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            Thread ship1 = new Ship(piers, 5, Arrays.asList(5, 25, 10)).downLoadContainers(2);
            ship1.start();
            ship1.
        });
    }
*/

        Ship ship = new Ship(piers, 20, new ArrayBlockingQueue<>(50, true, List.of (5,4,3) ))
                .upLoadContainers(List.of(1, 2, 3));

        List<Thread> shipList = Stream.of(
//                        new UpLoadCommand(new Ship(piers, 5, List.of(5, 25, 10))),
                        new Ship(piers, 15, new ArrayBlockingQueue<>(25, true, List.of(5, 6, 7)))
                                .upLoadContainers(List.of(1, 2, 3)),
                        new Ship(piers, 25, new ArrayBlockingQueue<>(30, true, List.of(8, 9, 10)))
                                .upLoadContainers(List.of(1, 2, 3)),
                        new Ship(piers, 54, new ArrayBlockingQueue<>(45, true, List.of(11, 12, 13)))
                                .upLoadContainers(List.of(1, 2, 3)),
                        new Ship(piers, 54, new ArrayBlockingQueue<>(45, true, List.of(11, 12, 13)))
                                .upLoadContainers(List.of(1, 2, 3))
                )
                .map(Thread::new)
                .peek(Thread::start)
                .collect(Collectors.toList());

        for (Thread thread1 : shipList) {
            thread1.join();
        }

    }
/*
        Port port = Port.getPort(new Ship(piers, 5, Arrays.asList(5, 25, 10)));
        System.out.println(port);
*/

/*        Port port = new Port(new Pier(), new Ship(piers, 5, Arrays.asList(5, 25, 10)));
        port.start();
        port.downLoadContainers(2);

    */

}
