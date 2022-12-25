package com.baris.service;

import com.baris.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    Boolean deleteUser(Long id);
}
