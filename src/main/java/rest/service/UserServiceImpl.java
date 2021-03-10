package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rest.dao.UserDao;
import rest.model.Role;
import rest.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getByEmail(username);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public User findUserByFirstName(String firstName) {
        return userDao.getByName(firstName);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public Role getRole(String name) {
        return userDao.getRole(name);
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }


}
