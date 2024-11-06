// src/main/java/com/example/demo/controller/UserController.java

package com.example.hello;

import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.http.HttpStatus;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // @GetMapping("/users")
    // public List<Map<String, Object>> getUsers() {
    // String sql = "SELECT * FROM users";
    // List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
    // return users;
    // }

    // @GetMapping("/users/{id}")
    // public Map<String, Object> getUserById(@PathVariable int id) {
    // String sql = "SELECT * FROM users WHERE id = ?";
    // Map<String, Object> user = jdbcTemplate.queryForMap(sql, id);
    // return user;
    // }

    @GetMapping("/users/{id}")
    public Map<String, Object> getUserById(@PathVariable int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            Map<String, Object> user = jdbcTemplate.queryForMap(sql, id);
            return user;
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException("User not found with id " + id);
        }
    }

    @GetMapping("/users")
    public List<Map<String, Object>> getUsers() {
        String sql = "SELECT * FROM users";
        List<Map<String, Object>> users = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> user = new HashMap<>();
            user.put("ID", rs.getInt("id"));
            user.put("Name", rs.getString("name"));
            user.put("Email", rs.getString("email"));
            return user;
        });
        return users;
    }

    @GetMapping("/users/search")
    public List<Map<String, Object>> searchUsers(@RequestParam String name) {
        String sql = "SELECT * FROM users WHERE name ILIKE ?";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql, "%" + name + "%");
        return users;
    }

}

// Custom Exception Class
@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
