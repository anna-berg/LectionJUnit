package concurrent.port;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Port extends Thread {

    //capacity - вместимость порта
    static BlockingQueue<Integer> containersInPort = new ArrayBlockingQueue<>(200, true, List.of(
            5,3,1,4,34,56,45));
    private static volatile Port port;
    private final Ship ship;

    private Port(Ship ship) {
        this.ship = ship;
    }

    public static Port getPort(Ship ship){
        Port rezult = port;
        if (rezult != null){
            return rezult;
        }
        synchronized (Port.class){
            if (port == null){
                port = new Port(ship);
            }
            return port;
        }
    }

    @Override
    public String toString() {
        return "Port{" +
                "ship=" + ship +
                '}';
    }
}
