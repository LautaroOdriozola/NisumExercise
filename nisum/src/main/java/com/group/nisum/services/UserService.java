package com.group.nisum.services;

import com.group.nisum.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long user_id);
    User saveUser(User user);
    Optional<User> getUserByEmail(String email);
    User updateTokenAndLastLogin(User user, String accessToken);
}
