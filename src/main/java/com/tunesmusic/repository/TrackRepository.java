package com.tunesmusic.repository;

import com.tunesmusic.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Long> {
    @Query(value = "select * from Track LIMIT 5",nativeQuery = true)
    List<Track> findTop5Track();

    @Query(value = "SELECT * FROM track WHERE track_name LIKE %?1%", nativeQuery = true)
    List<Track> findTrackByTrackName(String trackname);

    @Query(value = "SELECT * FROM track ORDER BY play_count desc  limit 5",nativeQuery = true)
    List<Track> findTop5TrackOrderByDesc();

    @Query(value = "SELECT * FROM track ORDER BY play_count desc limit 5",nativeQuery = true)
    List<Track> findAllTrackOrderByDesc();

    @Query(value = "SELECT * FROM TRACK WHERE ID_GENRE = ?1 order by play_count DESC LIMIT 5",nativeQuery = true)
    List<Track> find5TrackByIdGenre(Long idGenre);


    @Modifying
    @Transactional
    @Query(value = "update track set play_count = ?1 where id = ?2",nativeQuery = true)
    void  increasePlayCount(Long playCount,Long idTrack);

}
