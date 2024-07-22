package com.tunesmusic.service;

import com.tunesmusic.model.User;

public interface UserService {
    User findUserByUsername(String userName);
}
