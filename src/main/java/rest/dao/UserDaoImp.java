package rest.dao;


import org.springframework.stereotype.Repository;
import rest.model.Role;
import rest.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User getByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email=:email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public User getByName(String firstName) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.firstName=:first_name");
        query.setParameter("first_name", firstName);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> allUsers = entityManager.createQuery("SELECT u FROM User u", User.class);
        return allUsers.getResultList();
    }

    @Override
    public Role getRole(String name) {
        TypedQuery<Role> role = entityManager.createQuery("SELECT r FROM Role r where r.name = :name", Role.class)
                .setParameter("name", name);

        return role.getSingleResult();
    }

    @Override
    public boolean userExist(String email) {
        return getAll()
                .stream()
                .anyMatch((e) -> e.getEmail().hashCode() == email.hashCode());
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> roles = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return roles.getResultList();
    }
}

