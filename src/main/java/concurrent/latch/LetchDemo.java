package concurrent.latch;

import java.util.concurrent.CountDownLatch;

public class LetchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(RocketDetail.values().length);
    }
}
