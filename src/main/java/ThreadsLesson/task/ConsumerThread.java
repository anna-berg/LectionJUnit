package ThreadsLesson.task;

import java.util.Queue;

public class ConsumerThread implements Runnable{
    private final Queue<Integer> list;

    public ConsumerThread(Queue<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list){

            while (true){
                if(!list.isEmpty()) {
                    Integer removedValue = list.remove();
                    System.out.println("cousumer get value: " + removedValue + " list size " + list.size());
                } else {
                    System.out.println("consumer is waiting, list is empty");
                }
                try {
                    int random = 80;
//                    int random = RandomUtil.getRandom();
                    System.out.println("consumer wait: " + random);
                    list.wait(random);
//                    list.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
