package ThreadsLesson.task;

import java.util.Queue;

public class ProduserThread implements Runnable {

    private final Queue<Integer> list;

    public ProduserThread(Queue<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list){
            while (true){
                if (list.size() < 10){

                    int random = RandomUtil.getRandom();
                    list.add(random);
                    System.out.println("produser adds value: " + random + " list size " + list.size());
                } else {
                    System.out.println("produser do nothing");
                }
//                list.notifyAll();
                try {
//                    int randomWaitValue = RandomUtil.getRandom(3);
                    int randomWaitValue = 20;
                    System.out.println("produser waits: " + randomWaitValue);
                    list.wait(randomWaitValue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
