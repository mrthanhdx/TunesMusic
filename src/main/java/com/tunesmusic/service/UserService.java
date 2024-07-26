package com.tunesmusic.service;

import com.tunesmusic.model.User;

public interface UserService extends CommonService<User> {
    User findUserByUsername(String userName);
    void insertDefaultUserRole(Long userId);

    void insertFavoriteSong(Long userId,Long trackId);
}
