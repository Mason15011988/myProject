package by.my.project.repository;

import by.my.project.entity.DateOfBooking;
import by.my.project.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUser(User user) {
        return entityManager.createNamedQuery("User.findUser", user.getClass()).
                setParameter("email", user.getEmail()).
                setParameter("password", user.getPassword()).
                getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void addDate(DateOfBooking dateOfBooking) {
        entityManager.persist(dateOfBooking);
    }

    @Override
    public User findUserByEmail(String email) {
        return entityManager.createNamedQuery("User.findUserByEmail", User.class).
                setParameter("email", email).
                getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
}
