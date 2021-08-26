package UserService;
import UserService.Users;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService {
    private final List <Users> users = new ArrayList<Users>();

    public List<Users> getAll() {
        return users;
    }
    public boolean add(Users user) {
        return users.add(user);
    }
}
