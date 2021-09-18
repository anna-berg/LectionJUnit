package concurrent.port2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private int piersCount;
    private int containersCapacity;
    private int currentContainersCount;

    List<Thread> ships = new ArrayList<>();

    public Port(int piersCount, int containersCapacity, int currentContainersCount) {
        this.piersCount = piersCount;
        this.containersCapacity = containersCapacity;
        this.currentContainersCount = currentContainersCount;
    }

    public int getContainersCapacity() {
        return containersCapacity;
    }

    public int getCurrentContainersCount() {
        return currentContainersCount;
    }

    public void addContainers(int count) {
        currentContainersCount = currentContainersCount + count;
    }

    public void getContainers(int count) {
        currentContainersCount = currentContainersCount - count;
    }


    public void getAccessToPier() {
        boolean lockPort = reentrantLock.tryLock();
        try {
            //если все причалы заняты - нужно подождать
            while (piersCount == 0) {
                if (lockPort){
                    this.wait();
                }
            }
            if (lockPort){
                ships.add(Thread.currentThread());
                System.out.println(Thread.currentThread().getName() + " got access");
                piersCount--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lockPort){
                reentrantLock.unlock();
            }
        }
    }

    public void freeUpAccessToPier() {
        reentrantLock.lock();
        try {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + " is leaving pier");

            System.out.println("Current containers count in Port: " + currentContainersCount);

            if (ships.contains(thread)) {
                piersCount++;
            }
            ships.remove(thread);
            this.notifyAll();

        } finally {
            reentrantLock.unlock();
        }
    }

}