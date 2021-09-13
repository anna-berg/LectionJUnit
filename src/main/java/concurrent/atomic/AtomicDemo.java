package concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        //потокобезопасная оболочка над примитивным типом int
        AtomicInteger atomicInteger = new AtomicInteger();
        //выполняется через константу U - обьект кот. не использует синхронизаторов,
        // а работает непосредственно с памятью на самом низком уровне
        int value = atomicInteger.incrementAndGet();
        System.out.println(value);
    }
}
