package com.tunesmusic.repository;

import com.tunesmusic.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlbumRepository extends JpaRepository<Album,Long> {
    @Query(value = "select * from Album limit 5",nativeQuery = true)
    List<Album> getList5Album();
}
