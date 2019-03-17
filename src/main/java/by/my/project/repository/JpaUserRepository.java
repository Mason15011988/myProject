package by.my.project.repository;

import by.my.project.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLIntegrityConstraintViolationException;

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
        String sql = "from User where email = ?1 and password = ?2";
        return (User) entityManager.createQuery(sql).
                setParameter(1,user.getEmail()).
                setParameter(2,user.getPassword()).
                getSingleResult();
    }

    @Override
    public User findUserByEmail(String email) {
        String sql = "from User where email = ?1";
        return entityManager.createQuery(sql, User.class).
                setParameter(1,email).
                getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);

    }
}
