package com.ankit.userservice.service;

import com.ankit.userservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);

    // TODO: delete
    // TODO: update
}
