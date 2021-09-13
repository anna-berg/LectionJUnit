package concurrent.port;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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
        Queue<Pier> piers = new ArrayDeque<>(Arrays.asList(
                new Pier(),
                new Pier(),
                new Pier()
        ));

        List<Thread> shipList = Stream.of(
                        new Ship(piers, 5, Arrays.asList(5, 25, 10)),
                        new Ship(piers, 20, Arrays.asList(1, 2, 3)),
                        new Ship(piers, 15, Arrays.asList(5, 6, 7)),
                        new Ship(piers, 25, Arrays.asList(8, 9, 10)),
                        new Ship(piers, 54, Arrays.asList(11, 12, 13))
                )
                .map(Thread::new)
                .peek(Thread::start)
                .collect(Collectors.toList());
        for (Thread thread : shipList) {
            thread.join();
        }

        Port port = Port.getPort(new Ship(piers, 5, Arrays.asList(5, 25, 10)));
        System.out.println(port);

/*        Port port = new Port(new Pier(), new Ship(piers, 5, Arrays.asList(5, 25, 10)));
        port.start();
        port.downLoadContainers(2);*/

    }
}
