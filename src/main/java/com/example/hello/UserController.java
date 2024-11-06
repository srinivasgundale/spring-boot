// src/main/java/com/example/demo/controller/UserController.java

package com.example.hello;

import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/users")
    public List<Map<String, Object>> getUsers() {
        String sql = "SELECT * FROM users";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        return users;
    }

    @GetMapping("/users/{id}")
    public Map<String, Object> getUserById(@PathVariable int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        Map<String, Object> user = jdbcTemplate.queryForMap(sql, id);
        return user;
    }
}
