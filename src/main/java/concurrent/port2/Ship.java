package concurrent.port2;

import java.util.concurrent.locks.ReentrantLock;

public class Ship extends Thread {
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private int containersToUpload;
    private int containersToUnload;
    private Port port;


    public Ship(Port port, String name, int containersToUpload, int containersToUnload) {
        super(name);
        this.port = port;
        this.containersToUpload = containersToUpload;
        this.containersToUnload = containersToUnload;

        //пускаем корабль в плавание
        start();
    }

    @Override
    public void run() {
        boolean isChanged = false;

        try {

            while (true) {
                if (!isChanged) {
                    port.getAccessToPier();
                }

                isChanged = false;
                if (containersToUnload != 0 && containersToUpload != 0) {
                    containersToUpload--;
                    containersToUnload--;
                    isChanged = true;
                } else {
                    if (containersToUnload != 0) {
                        isChanged = unloadContainers();
                    } else {
                        if (containersToUpload != 0) {
                            isChanged = uploadContainers();
                        } else {
                            System.out.println(Thread.currentThread().getName() + " has finished his upload/unload");
                            port.freeUpAccessToPier();
                            break;
                        }
                    }
                }

                if (isChanged) {
                    Thread.sleep(10);
                } else {
                    port.freeUpAccessToPier();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private boolean unloadContainers() {
        boolean unloaded = false;
        reentrantLock.lock();
        try {
            if (port.getContainersCapacity() > port.getCurrentContainersCount()) {
                port.addContainers(containersToUnload);
                containersToUnload = 0;
                unloaded = true;
            } else {
                System.out.println("Port is full");
            }
        } finally {
            reentrantLock.unlock();
        }
        return unloaded;
    }

    private boolean uploadContainers() {
        boolean uploaded = false;
        reentrantLock.lock();
        try {
            if (port.getCurrentContainersCount() > 0) {
                port.getContainers(containersToUpload);
                containersToUpload = 0;
                uploaded = true;
            } else {
                System.out.println("Port doesn't have containers to upload ship");
            }
        } finally {
            reentrantLock.unlock();
        }
        return uploaded;
    }
}