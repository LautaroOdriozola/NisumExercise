package com.group.nisum.services.servicesImpl;

import com.group.nisum.entity.Phone;
import com.group.nisum.entity.User;
import com.group.nisum.repository.PhoneRepository;
import com.group.nisum.repository.UserRepository;
import com.group.nisum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long user_id) {
        return userRepository.findById(user_id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        String pass = user.getPassword();
        String passEncode = passwordEncoder.encode(pass);
        user.setPassword(passEncode);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User updateTokenAndLastLogin(User user, String accessToken) {
        long now = System.currentTimeMillis();
        Timestamp timestampNow = new Timestamp(now);
        user.setLast_login(timestampNow);
        user.setToken(accessToken);

        return userRepository.save(user);
    }


}
