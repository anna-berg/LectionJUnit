package concurrent.port2;

import java.util.*;

/*
* 1. Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров. Число контейнеров,
находящихся в текущий момент в порту и на корабле, должно быть неотрицательным и
превышающим заданную грузоподъемность судна и вместимость порта. В порту работает
несколько причалов. У одного причала может стоять один корабль. Корабль может загружаться у
причала, разгружаться или выполнять оба действия.
* */

public class PortRunner {
    public static void main(String[] args) {
        Port port = new Port(4, 3400, 1100);

        List<Ship> ships = new ArrayList<>();

        ships.add(new Ship(port, "Ship 1", 260, 0));
        ships.add(new Ship(port, "Ship 2", 200, 10));
        ships.add(new Ship(port, "Ship 3", 100, 100));
        ships.add(new Ship(port, "Ship 4", 120, 150));
        ships.add(new Ship(port, "Ship 5", 120, 150));


        for (Ship ship : ships) {
            try {
                ship.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}