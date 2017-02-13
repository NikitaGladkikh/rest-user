package dao;

import domain.User;

import java.util.List;

/**
 * Created by KO on 11.02.2017.
 */
public interface IUserDao {

    List<User> findAll();

    User findById(String id);

    User insert(User user);

    User update(User user);

    User delete(String id);
}
