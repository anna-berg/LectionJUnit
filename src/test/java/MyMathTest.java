import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyMathTest {

    @Test// (expected = ArithmeticException.class)
    public void zeroDenominatorShouldThrowExeption() {
        MyMath.divide(1, 0);
    }
}