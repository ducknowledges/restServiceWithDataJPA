package com.github.kononovit.service.controller;

import com.github.kononovit.service.dao.UserDAO;
import com.github.kononovit.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserBy(@PathVariable int id) {
        return userDAO.getUserBy(id);
    }

    @GetMapping("/age")
    private List<User> getUsersByAge(@RequestParam int from, @RequestParam int to) {
        int younger = Math.min(from, to);
        int older = Math.max(from, to);

        return userDAO.getUsersByAscAge(younger, older);
    }

    @PostMapping()
    public void createUser(@RequestParam String name, @RequestParam int age) {
        userDAO.crateUser(new User(name, age));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userDAO.deleteUserBy(id);
    }
}
