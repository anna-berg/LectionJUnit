package concurrent.account;

public class AccountThread extends Thread {
    private final Accaunt accauntFrom;
    private final Accaunt accauntTo;

    public AccountThread(Accaunt accauntFrom, Accaunt accauntTo) {
        this.accauntFrom = accauntFrom;
        this.accauntTo = accauntTo;
    }
    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            lockAccounts();
            try {
                if(accauntFrom.takeOff(10)){
                    accauntTo.add(10);
                }
            } finally {
                accauntFrom.getLock().unlock();
                accauntTo.getLock().unlock();
            }


        }
    }

    private void lockAccounts() {
        while (true){
            boolean fromLockRezult = accauntFrom.getLock().tryLock();
            boolean toLockRezult = accauntFrom.getLock().tryLock();
            if (fromLockRezult && toLockRezult){
                break;
            }
            if (fromLockRezult){
                accauntFrom.getLock().unlock();
            }
            if (toLockRezult){
                accauntTo.getLock().unlock();
            }
        }
    }


}
