package concurrent.port;

import java.util.ArrayList;
import java.util.List;

public class Port extends Thread {
    static List<Integer> containersInPort = new ArrayList<>();
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

    public void upLoadContainers(List <Integer> containerstoUpload){
        synchronized (ship){
                for (int i = 0; i < containerstoUpload.size(); i++) {
                    if(ship.getContainersInShip().size() < ship.getShipCapacity()) {
                        ship.setContainersInShip(containerstoUpload.remove(i));
                        System.out.println("In to ship: " + ship.toString() + " upload " + containerstoUpload.size() + " containers!");
                    } else {
                        System.out.println("Ship is full!");
                    }
                }
        }
    }

    public synchronized void downLoadContainers(int count){
        synchronized (ship) {
            for (int i = 0; i < count; i++) {
                    Port.containersInPort.add(ship.removeContainersFromShip(i));
            }
        }
    }

    public synchronized void uploadAndDownloadContainers(List<Integer> containersToUpload, int count){
        upLoadContainers(containersToUpload);
        downLoadContainers(count);
    }

    @Override
    public String toString() {
        return "Port{" +
                "ship=" + ship +
                '}';
    }
}
