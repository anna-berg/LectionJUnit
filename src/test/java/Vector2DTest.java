import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {
    private final double EPS = 1e-9;
    private Vector2D v1;

    @Before
    public void createNewVector(){
        v1 = new Vector2D();
    }

    @Test
    void getXShouldHaveZeroX() {
        Assertions.assertEquals(0, v1.getX(), EPS);
    }

    @Test
    void getXShouldHaveZeroY() {
        Assertions.assertEquals(0, v1.getY(), EPS);
    }

    @Test
    void newLengthShouldHaveZeroLength() {
        Assertions.assertEquals(0, v1.length(), EPS);
    }
}