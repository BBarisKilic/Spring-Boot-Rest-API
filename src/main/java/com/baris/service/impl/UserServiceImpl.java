package com.baris.service.impl;

import com.baris.advice.UserNotFoundException;
import com.baris.dto.UserDto;
import com.baris.entity.User;
import com.baris.repository.UserRepository;
import com.baris.service.UserService;
import com.baris.util.CustomPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

        if(user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }

        throw new UserNotFoundException("Kullanici bulunamadi");
    }

    @Override
    public Page<User> getUserByPage(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);

        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getUserByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Slice<User> getUserSlice(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public CustomPage<User, UserDto> getUserCustomPage(Pageable pageable) {
        final Page<User> data = userRepository.findAll(pageable);
        final UserDto[] dtos = modelMapper.map(data.getContent(), UserDto[].class);
        return new CustomPage<>(data, Arrays.asList(dtos));
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

        throw new UserNotFoundException("Kullanici bulunamadi");
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
