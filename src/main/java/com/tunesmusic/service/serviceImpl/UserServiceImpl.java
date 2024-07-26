package com.tunesmusic.service.serviceImpl;

import com.tunesmusic.model.User;
import com.tunesmusic.repository.UserRepository;
import com.tunesmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public User findUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public void insertDefaultUserRole(Long userId) {
        userRepository.insertUserRole(userId,Long.valueOf(1));
    }

    @Override
    public void insertFavoriteSong(Long userId, Long trackId) {
        userRepository.insertFavoriteSong(userId,trackId);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
