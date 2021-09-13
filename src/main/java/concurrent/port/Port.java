package concurrent.port;

import java.util.ArrayList;
import java.util.List;

public class Port extends Thread {
    static List<Integer> containersInPort = new ArrayList<>();
    private final Pier pier;
    private final Ship ship;

    public Port(Pier pier, Ship ship) {
        this.pier = pier;
        this.ship = ship;
    }

    public void upLoadContainers(List <Integer> containerstoUpload){
        synchronized (ship){
            synchronized (pier){
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
    }

    public synchronized void downLoadContainers(int count){
        synchronized (ship) {
            synchronized (pier) {
                for (int i = 0; i < count; i++) {
                    Port.containersInPort.add(ship.removeContainersFromShip(i));
                }
            }
        }
    }

    public synchronized void uploadAndDownloadContainers(List<Integer> containersToUpload, int count){
        upLoadContainers(containersToUpload);
        downLoadContainers(count);
    }

}
