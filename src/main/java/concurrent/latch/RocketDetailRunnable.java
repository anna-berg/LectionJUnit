package concurrent.latch;

import java.util.concurrent.CountDownLatch;

public class RocketDetailRunnable implements Runnable{

    private final RocketDetail rocketDetail;
    private final CountDownLatch countDownLatch;

    public RocketDetailRunnable(RocketDetail rocketDetail, CountDownLatch countDownLatch) {
        this.rocketDetail = rocketDetail;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Detail prepearing..." + rocketDetail);
        try {
            Thread.sleep(1000);
            System.out.println("Detail is ready" + rocketDetail);
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
