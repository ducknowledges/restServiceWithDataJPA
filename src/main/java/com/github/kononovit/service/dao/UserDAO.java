package com.github.kononovit.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.github.kononovit.service.models.User;

import java.util.List;
import java.util.ArrayList;
import java.sql.Types;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    public User getUserBy(int id) {
        String sql = "SELECT * FROM Users WHERE id=?";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[] {id},
                new int[] {Types.INTEGER},
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    public List<User> getUsersByAscAge(int from, int to) {
        String sql = "SELECT * FROM Users " +
                "WHERE age BETWEEN ? AND ?" +
                "ORDER BY age ASC";

        return jdbcTemplate.query(
                sql,
                new Object[] {from, to},
                new int[] {Types.INTEGER, Types.INTEGER},
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    public void crateUser(User user) {
        String sql = "INSERT INTO Users (name, age) VALUES(?, ?)";

        jdbcTemplate.update(sql, user.getName(), user.getAge());
    }

    public void deleteUserBy(int id) {
        String sql = "DELETE FROM Users WHERE id=?";

        jdbcTemplate.update(sql, id);
    }
}
