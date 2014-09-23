
package ohtu.data_access;

import java.util.List;
import ohtu.domain.User;

public interface UserDao {
    void add(User user);

    User findByName(String name);

    List<User> listAll();
}
