package com.example.hello;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TestController {
    @GetMapping("/test")

    public String test() {
        return "Hello, World!";
    }

    @GetMapping("/hello")
    public Map<String, String> sayHello() {
        return Map.of("message", "Hello, World!");
    }

    @GetMapping("/hello/{name}")
    public Map<String, String> greetUser(@PathVariable String name) {
        return Map.of("message", "Hello, " + name + "!");
    }

    @GetMapping("/fruits")
    public List<String> getFruits() {
        return List.of("Apple", "Banana", "Cherry");
    }

    @GetMapping("/user")
    public Map<String, Object> getUser() {
        return Map.of(
                "name", "John Doe",
                "age", 30,
                "email", "john.doe@example.com",
                "roles", List.of("USER", "ADMIN"));
    }

    @GetMapping("/add")
    public Map<String, Integer> addNumbers(@RequestParam int a, @RequestParam int b) {
        int sum = a + b;
        return Map.of("sum", sum);
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> getStatus() {
        return ResponseEntity
                .status(202)
                .body(Map.of("message", "Accepted"));
    }
}
