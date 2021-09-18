package concurrent.port;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Ship implements Runnable{

    private final BlockingQueue<Pier> piers;
    private BlockingQueue<Integer> containersInShip;
    private int shipCapacity;

    public Ship(BlockingQueue<Pier> piers, int shipCapacity, BlockingQueue<Integer> containersInShip) {
        this.containersInShip = containersInShip;
        this.shipCapacity = shipCapacity;
        this.piers = piers;
    }
/*    public BlockingQueue<Pier> getPiers() {
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
    }*/

    public synchronized void setShipCapacity(int shipCapacity) {
        this.shipCapacity = shipCapacity;
    }

    @Override
    public void run() {
        try {
            Pier pier = piers.take();
            System.out.println("Ship: " + Thread.currentThread().getName()
                    + " in a port: " + pier);
            Thread.sleep(5L);

            piers.add(pier);
            System.out.println("Ship: " + Thread.currentThread().getName()
                    + " leaving port: " + pier);
/*            synchronized (piers) {
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
            }*/
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Ship upLoadContainers(List <Integer> containerstoUpload){
        List<Integer> containersToUpload = new ArrayList<>(containerstoUpload);
        int count = 0;
        for (int i = 0; i < containersToUpload.size(); i++) {
            if(containersInShip.size() < shipCapacity) {
                containersInShip.add(containersToUpload.remove(i));
                count++;
            } else {
                System.out.println("Ship is full!");
            }
        }
        System.out.println("In a ship " + Thread.currentThread().getName() + " uploaded " + count + " containers");
        return this;
    }

    public synchronized Ship downLoadContainers(int count){
        for (int i = 0; i < count; i++) {
            try {
                Port.containersInPort.add(containersInShip.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("From a ship " + Thread.currentThread().getName() + " downloaded " + count + " containers");
        return this;
    }

    public synchronized Ship uploadAndDownloadContainers(List<Integer> containersToUpload, int count){
        upLoadContainers(containersToUpload);
        downLoadContainers(count);
        return this;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "piers=" + piers +
                ", containersInShip=" + containersInShip +
                '}';
    }
}

/*interface Command {
    public void execute(Thread thread);
}

class DownLoadCommand implements Command{
    int count;
    public DownLoadCommand(int count) {
        this.count = count;
    }

    @Override
    public void execute(Thread thread) {
        ship.downLoadContainers(count);
    }
}
class UpLoadCommand implements Command {
    Ship ship;
    List <Integer> containerstoUpload;

    public UpLoadCommand(List <Integer> containerstoUpload) {
        this.containerstoUpload = containerstoUpload;
    }

    @Override
    public void execute(Thread thread) {
        ship.upLoadContainers(containerstoUpload);
    }
}
class UploadAndDownloadCommand implements Command {
    Ship ship;
    List <Integer> containerstoUpload;
    int count;

    public UploadAndDownloadCommand(Ship ship, int count, List <Integer> containerstoUpload) {
        this.ship = ship;
        this.count = count;
        this.containerstoUpload = containerstoUpload;
    }

    @Override
    public void execute(Thread thread) {
        ship.uploadAndDownloadContainers(containerstoUpload, count);
    }
}

class Action{
    Command download;
    Command upload;
    Command downloadAndUpload;

    public Action(DownLoadCommand download) {
        this.download = download;
    }
    public Action(UpLoadCommand upload) {
        this.upload = upload;
    }
    public Action(UploadAndDownloadCommand uploadAndDownload) {
        this.downloadAndUpload = uploadAndDownload;
    }

    public void download(){
        download.execute(Thread.currentThread());
    }
    public void upload(){
        upload.execute(Thread.currentThread());
    }

    public void downloadAndUpload(){
        downloadAndUpload.execute(Thread.currentThread());
    }

}*/
