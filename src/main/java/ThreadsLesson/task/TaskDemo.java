package ThreadsLesson.task;

import java.util.LinkedList;
import java.util.Queue;

public class TaskDemo {
    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> queue = new LinkedList<>();
        Thread produserThread = new Thread( new ProduserThread(queue));
        Thread consumerThread = new Thread (new ConsumerThread(queue));

        produserThread.start();
        consumerThread.start();

        produserThread.join();
        consumerThread.join();
    }
}
