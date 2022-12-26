package com.baris.service;

import com.baris.dto.UserDto;
import com.baris.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();
    UserDto getUserById(Long id);
    Page<User> getUserByPage(int page, int size);
    Page<User> getUserByPage(Pageable pageable);
    UserDto updateUser(Long id, UserDto userDto);
    Boolean deleteUser(Long id);
}
