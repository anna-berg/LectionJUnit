import UserService.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {
    @Test
    void test (){
        UserService userService = new UserService();
        List<Users> usersList1 = userService.getAll();
        assertTrue(usersList1.isEmpty());
    }
}
