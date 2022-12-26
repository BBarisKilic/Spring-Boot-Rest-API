package com.baris.service;

import com.baris.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    Boolean deleteUser(Long id);
}
