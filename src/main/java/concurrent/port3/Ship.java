package concurrent.port3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Ship implements Runnable {
    private final BlockingQueue<Pier> piers;
    private int containersToUpload;
    private int containersToUnload;
    private int shipCapacity;

    public Ship(BlockingQueue<Pier> piers, int containersToUpload, int containersToUnload, int shipCapacity) {
        this.piers = piers;
        if (containersToUpload >= 0){
            this.containersToUpload = containersToUpload;
        } else {
            System.out.println("Containers to upload should be positive!");
        }
        if (containersToUnload >= 0){
            this.containersToUnload = containersToUnload;
        } else {
            System.out.println("Containers to unload should be positive!");
        }
        if (shipCapacity >= 0) {
            this.shipCapacity = shipCapacity;
        } else {
            System.out.println("Ship capacity should be positive!");
        }
    }

    @Override
    public void run() {
        try {
            AtomicInteger uploadedCounter = new AtomicInteger();
            AtomicInteger unloadedCounter = new AtomicInteger();
            boolean isChanged = false;

            Pier pier = piers.take();
            System.out.println(Thread.currentThread().getName() + " in a port " + pier);
            int freeSpaceInPort = Port.containersCapacity.incrementAndGet() - Port.currentContainersCount.incrementAndGet();
            while (containersToUnload != 0 && freeSpaceInPort > containersToUnload){
                containersToUnload--;
                unloadedCounter.incrementAndGet();
                Port.currentContainersCount.incrementAndGet();
            }
            System.out.println(unloadedCounter + " container unload from the ship " + Thread.currentThread().getName());

            while (containersToUpload != 0) {
                if (containersToUpload < shipCapacity) {
                    containersToUpload--;
                    uploadedCounter.incrementAndGet();
                } else {
                    System.out.println("Ship capacity " + shipCapacity + " not enough to upload " + containersToUpload + " containers");
                    break;
                }
            }

            System.out.println(uploadedCounter + " container upload to the ship " + Thread.currentThread().getName());

            Thread.sleep(5L);

            System.out.println(Thread.currentThread().getName() + " leaving a port " + pier);
            piers.add(pier);

            /*synchronized (piers){
            while (true){
                if (!piers.isEmpty()) {
                    Pier pier = piers.remove();
                    System.out.println(Thread.currentThread().getName() + " in a port " + pier);

                    piers.wait(5L);
                    System.out.println(Thread.currentThread().getName() + " leaving a port " + pier);
                    piers.add(pier);
                    piers.notifyAll();
                    break;
                } else {
                    piers.wait();
                    System.out.println(Thread.currentThread().getName() + " waiting for a free pier");
                }
            }
        }*/

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
