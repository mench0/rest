package rest.dao;



import rest.model.Role;
import rest.model.User;

import java.util.List;

public interface UserDao {
    User getByEmail(String email);
    User getByName(String name);
    void save(User user);
    User getById(Long id);
    void update(User user);
    void delete(Long id);
    List<User> getAll();
    Role getRole(String name);
    List<Role> getAllRoles();
}
