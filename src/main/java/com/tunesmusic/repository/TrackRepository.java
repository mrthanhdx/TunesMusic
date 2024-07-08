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


}
