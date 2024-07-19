package com.tunesmusic.repository;

import com.tunesmusic.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
    @Query(value = "select * from Artist LIMIT 5" ,nativeQuery = true)
    List<Artist> getList5Artist();
}