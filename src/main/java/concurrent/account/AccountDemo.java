package concurrent.account;

public class AccountDemo {
    public static void main(String[] args) throws InterruptedException {
        Accaunt accaunt1 = new Accaunt(20000);
        Accaunt accaunt2 = new Accaunt(20000);

        AccountThread accountThread = new AccountThread(accaunt1, accaunt2);
        AccountThread accountThread1 = new AccountThread(accaunt2, accaunt1);

        accountThread.start();
        accountThread1.start();

        accountThread.join();
        accountThread1.join();

        System.out.println(accaunt1);
        System.out.println(accaunt2);

    }
}
