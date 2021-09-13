package ThreadsLesson.task;

import java.util.Random;

public final class RandomUtil {
        private static final Random RANDOM = new Random();
        private static final int DEFAULT_BOUND = 10;

        private RandomUtil() {
        }

        //генерирует рандомные числа от 0-DEFAULT_BOUND
        public static int getRandom(){
                return RANDOM.nextInt(DEFAULT_BOUND);
        }

        //генерирует установленные значения
        public static int getRandom(int bound){
                return RANDOM.nextInt(bound);
        }
}
