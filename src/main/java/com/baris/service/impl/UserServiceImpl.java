package com.baris.service.impl;

import com.baris.entity.User;
import com.baris.repository.UserRepository;
import com.baris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setCreationDate(new Date());
        user.setCreatedBy("Admin");
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);

    }

    @Override
    public User updateUser(Long id, User user) {
        final Optional<User> resultUser = userRepository.findById(id);

        if(resultUser.isPresent()) {
            resultUser.get().setFirstName(user.getFirstName());
            resultUser.get().setLastName(user.getLastName());
            resultUser.get().setUpdatedAt(new Date());
            resultUser.get().setUpdatedBy("Admin");
            return userRepository.save(resultUser.get());
        }

        return null;
    }
}
