package controller;

import dao.IUserDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        User user = userDao.findById(id);
        return null != user
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(String.format("User not found id=[%s]", id), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        return new ResponseEntity<>(userDao.insert(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        return null != userDao.delete(id)
                ? new ResponseEntity<>(id, HttpStatus.OK)
                : new ResponseEntity<>(String.format("User not found id=[%s]", id), HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        user = userDao.update(user);
        return null != user
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(String.format("User not found id=[%s]", id), HttpStatus.NOT_FOUND);
    }
}
