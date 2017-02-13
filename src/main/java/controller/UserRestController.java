package controller;

import dao.IUserDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by KO on 11.02.2017.
 */
@Controller
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private IUserDao userDao;

    @GetMapping
    public ResponseEntity getUsers() {
        return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable String id) {
        return new ResponseEntity<>(userDao.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        userDao.insert(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        if (null == userDao.delete(id)) {
            return new ResponseEntity<>("No User found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        user = userDao.update(user);
        if (null == user) {
            return new ResponseEntity<>("No User found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostConstruct
    void initData() {
        userDao.insert(new User("John", "Whiley"));
        userDao.insert(new User("Benedict", "Cumbercratch"));
        userDao.insert(new User("Sara", "Conor"));
        userDao.insert(new User("Mace", "Windu"));
        userDao.insert(new User("Jack", "Sparrow"));
        userDao.insert(new User("John", "Snow"));
        userDao.insert(new User("Chip", "Dale"));
        userDao.insert(new User("Glint", "Vine"));
        userDao.insert(new User("Jeepers", "Creepers"));
    }

}
