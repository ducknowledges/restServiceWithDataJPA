package com.github.kononovit.serviceDataJPA.controller;

import com.github.kononovit.serviceDataJPA.models.User;
import com.github.kononovit.serviceDataJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserRepository repository;

    @Autowired
    public UsersController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @GetMapping("/age")
    private List<User> getUsersByAge(@RequestParam int from, @RequestParam int to) {
        int younger = Math.min(from, to);
        int older = Math.max(from, to);

        return repository.findByAgeRange(younger, older);
    }

    @PostMapping()
    public void createUser(@RequestParam String name, @RequestParam int age) {
        User user = new User(name, age);
        repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }
}
