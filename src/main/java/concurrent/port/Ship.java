package concurrent.port;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class Ship implements Runnable{

    private final Queue<Pier> piers;
    private List<Integer> containersInShip;
    private int shipCapacity;

    public Queue<Pier> getPiers() {
        return piers;
    }
    public List<Integer> getContainersInShip() {
        return containersInShip;
    }
    public int getShipCapacity() {
        return shipCapacity;
    }

    public synchronized void setContainersInShip(int container) {
        this.containersInShip.add(container);
    }
    public synchronized int removeContainersFromShip(int container) {
        this.containersInShip.remove(container);
        return container;
    }
    public synchronized void setShipCapacity(int shipCapacity) {
        this.shipCapacity = shipCapacity;
    }

    public Ship(Queue<Pier> piers, int shipCapacity, List<Integer> containersInShip) {
        this.containersInShip = containersInShip;
        this.shipCapacity = shipCapacity;
        this.piers = piers;
    }

    @Override
    public void run() {
        try {
            synchronized (piers) {
                while (true) {
                    if (!piers.isEmpty()) {
                        Pier currentPier = piers.remove();
                        System.out.println("Ship: " + Thread.currentThread().getName()
                                + " in a port: " + currentPier);

                        piers.wait(5L);

                        System.out.println("Ship: " + Thread.currentThread().getName()
                                + " leaving port: " + currentPier);
                        piers.add(currentPier);
                        piers.notifyAll();
                        break;
                    } else {
                        piers.wait();
                        System.out.println("Ship: " + Thread.currentThread().getName()
                                + " waiting for a free pier");
                    }
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Ship{" +
                "piers=" + piers +
                ", containersInShip=" + containersInShip +
                '}';
    }
}
