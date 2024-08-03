package com.tunesmusic.repository;

import com.tunesmusic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String userName);
    @Modifying
    @Transactional
    @Query(value = "insert into user_role(user_id,role_id) values(?1,?2)",nativeQuery = true)
    void insertUserRole(Long userId,Long roleId);

    @Modifying
    @Transactional
    @Query(value = "insert into user_favorite(user_id,track_id) values(?1,?2)",nativeQuery = true)
    void insertFavoriteSong(Long userId,Long trackId);

    @Modifying
    @Transactional
    @Query(value = "delete from user_following where user_id = ?1 and artist_id = ?2",nativeQuery = true)
    void unfollowArtist(Long userId,Long artistId);

    @Modifying
    @Transactional
    @Query(value = "insert into user_following (user_id,artist_id) values (?1,?2)",nativeQuery = true)
    void followArtist(Long userId,Long artistId);
}
