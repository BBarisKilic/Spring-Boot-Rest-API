package com.baris.api;

import com.baris.entity.User;
import com.baris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        final User resultUser = userService.createUser(user);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getUsers() {
        final List<User> resultUsers = userService.getUsers();
        return ResponseEntity.ok(resultUsers);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        final User resultUser = userService.getUserById(id);
        return ResponseEntity.ok(resultUser);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id,@RequestBody User user) {
        final User resultUser = userService.updateUser(id, user);
        return ResponseEntity.ok(resultUser);
    }
}
