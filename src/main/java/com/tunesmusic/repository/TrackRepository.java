package com.tunesmusic.repository;

import com.tunesmusic.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Long> {
    @Query(value = "select * from Track LIMIT 5",nativeQuery = true)
    List<Track> findTop5Track();

    @Query(value = "SELECT * FROM track WHERE track_name LIKE %?1%", nativeQuery = true)
    List<Track> findTrackByTrackName(String trackname);

    @Query(value = "SELECT * FROM track ORDER BY play_count desc ",nativeQuery = true)
    List<Track> findTop5TrackOrderByDesc();

    @Query(value = "SELECT * FROM track ORDER BY play_count desc limit 5",nativeQuery = true)
    List<Track> findAllTrackOrderByDesc();
}
