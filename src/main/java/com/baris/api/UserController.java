package com.baris.api;

import com.baris.dto.UserDto;
import com.baris.entity.User;
import com.baris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        final UserDto resultUserDto = userService.createUser(userDto);
        return ResponseEntity.ok(resultUserDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getUsers() {
        final List<UserDto> resultUserDtos = userService.getUsers();
        return ResponseEntity.ok(resultUserDtos);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        final UserDto resultUser = userService.getUserById(id);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getByPage")
    public ResponseEntity<Page<User>> getUserByPage(@RequestParam int currentPage, @RequestParam int pageSize) {
        final Page<User> resultUser = userService.getUserByPage(currentPage, pageSize);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getByPage/v1")
    public ResponseEntity<Page<User>> getUserByPage(Pageable pageable) {
        final Page<User> resultUser = userService.getUserByPage(pageable);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getSlice")
    public ResponseEntity<Slice<User>> getUserSlice(Pageable pageable) {
        final Slice<User> resultUser = userService.getUserSlice(pageable);
        return ResponseEntity.ok(resultUser);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        final UserDto resultUserDto = userService.updateUser(id, userDto);
        return ResponseEntity.ok(resultUserDto);
    }

    @DeleteMapping("/removeById/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
        final Boolean result = userService.deleteUser(id);
        return ResponseEntity.ok(result);
    }
}
