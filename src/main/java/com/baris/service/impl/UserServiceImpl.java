package com.baris.service.impl;

import com.baris.dto.UserDto;
import com.baris.entity.User;
import com.baris.repository.UserRepository;
import com.baris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        final User user = modelMapper.map(userDto, User.class);
        user.setCreationDate(new Date());
        user.setCreatedBy("Admin");
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        final List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        final Optional<User> user = userRepository.findById(id);

        return user
                .map(value -> modelMapper.map(value, UserDto.class))
                .orElse(null);
    }

    @Override
    public Page<User> getUserByPage(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);

        return userRepository.findAll(pageable);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        final Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            user.get().setFirstName(userDto.getFirstName());
            user.get().setLastName(userDto.getLastName());
            user.get().setUpdatedAt(new Date());
            user.get().setUpdatedBy("Admin");
            return modelMapper.map(userRepository.save(user.get()), UserDto.class);
        }

        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        final Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
