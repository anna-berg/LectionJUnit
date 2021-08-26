import UserService.Users;
import UserService.UserService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
/*
PER_CLASS -  создается только один обьект класса для всех тестов
PER_METHOD - создается новый обьект класса для каждого метода
             методы @BeforeAll & @AfterAll в таком случае должны быть статичными
 */
public class UserServiceTest {
    private UserService userService;

    @BeforeAll
    void createConnection(){
        System.out.println("Create Connection " + this);
    }

    @BeforeEach
    void prepare (){
        System.out.println("Before each " + this);
        userService = new UserService();

    }

    @Test
    void usersEmptyIfNoUsersAdded (){
        System.out.println("Test 1: usersEmptyIfNoUsersAdded " + this);

        List<Users> usersList1 = userService.getAll();
        assertTrue(usersList1.isEmpty());
    }

    @Test
    void userSizeIfUserAdded(){
        System.out.println("Test 2: userSizeIfUserAdded " + this);

        userService.add(new Users());
        userService.add(new Users());
        userService.add(new Users());
        List<Users> usersList1 = userService.getAll();
        assertEquals(3, usersList1.size());
    }

    @AfterEach
    void deleteDataFromDatabase(){
        System.out.println("After Each " + this);

    }

    @AfterAll
    void closeConnection(){
        System.out.println("AfterAll " + this);
    }
}
