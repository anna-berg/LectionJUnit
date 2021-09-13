package ThreadsLesson.queue;

import java.util.Queue;
/*
* ФУНКЦИОНАЛЬНЫЕ ИНТЕРФЕЙСЫ
* (содержат одну единственную функцию)
* ----------------------------------
* function - параметр. двумя типами, один принимает, один возвращает
* predicate - принимает на вход один параметр, возвращает boolean
* consumer (потребитель) - параметризован одним типом, принимает что-то на вход, но ничего не возвращает
* supplier (продьюсер) - тоже параметризован одним типом, ничего не принимает, но что-то возвращает
* BiFunction - параметризована тремя параметрами (два принимаемых, один возвращаемый) напр- compare
* */
public class BuyerThread implements Runnable {

    private final Queue<Cashbox> cashboxes;

    public BuyerThread(Queue<Cashbox> cashboxe) {
        this.cashboxes = cashboxe;
    }
    @Override
    public void run() {
        try {
            while(true){
                synchronized (cashboxes){
                    if (!cashboxes.isEmpty()) {
                        Cashbox cashbox = cashboxes.remove();
                        System.out.println(Thread.currentThread().getName() + " обслуживается в кассе " + cashboxes);
                            // не освобождает монитор -> другие потоки не могут работать в этом цикле
//                          Thread.sleep(5L);
                            //вызываем wait у объекта, монитор которого мы захватили
                            //освобождаем монитор кешбоксес -> другие потоки могут захватить монитор в строчке 17
                            cashboxes.wait(5L);
                            //прежде чем перейти к выполнению строки 28, мы вновь должны захватить монитор

                        System.out.println(Thread.currentThread().getName() + " освобождаю кассу " + cashboxes);
                        cashboxes.add(cashbox);
                        //уведомляем все потоки, что касса освободилась
                        cashboxes.notifyAll();
                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName() + " ожидаю свободную кассу " + cashboxes);
//                        Thread.sleep(5L); //не освобождает монитор
                        cashboxes.wait(); // освобождает монитор
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}