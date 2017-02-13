package dao;

import domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by KO on 11.02.2017.
 */
@Repository
public class UserDao implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User findById(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public User insert(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public User update(User updatedUser) {
        entityManager.merge(updatedUser);
        return updatedUser;
    }

    @Override
    @Transactional
    public User delete(String id) {
        User user = findById(id);
        entityManager.remove(user);
        return user;
    }
}
