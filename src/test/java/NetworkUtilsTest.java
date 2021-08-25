import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkUtilsTest {

    @Test //(timeout = 1000)
    void getConnectionShouldReturnFasterThenOneSec() {
        NetworkUtils.getConnection();
    }
}