package com.tunesmusic.service.serviceImpl;

import com.tunesmusic.model.User;
import com.tunesmusic.repository.UserRepository;
import com.tunesmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User findUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
