package concurrent.account;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Accaunt {

    private final Lock lock = new ReentrantLock();
    private static int generator = 1;
    private int id;
    private int money;

    public Accaunt(int money) {
        this.id = generator++;
        this.money = money;
    }

    public void add (int money){
        this.money += money;
    }

    public boolean takeOff (int money){
        if (this.money >= money){
            this.money -= money;
            return true;
        }
        return false;
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Accaunt{" +
                "lock=" + lock +
                ", id=" + id +
                ", money=" + money +
                '}';
    }
}
