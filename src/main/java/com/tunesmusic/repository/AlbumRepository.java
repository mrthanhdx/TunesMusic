package com.tunesmusic.repository;

import com.tunesmusic.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface AlbumRepository extends JpaRepository<Album,Long> {
    @Query(value = "select * from Album limit 5",nativeQuery = true)
    List<Album> getList5Album();
    @Query(value = "select  * from album where id_artist = ?1",nativeQuery = true)
    List<Album> getListAlbumByArtistId(Long artistId);

    @Modifying
    @Transactional
    @Query(value = "insert into album_track(album_id,track_id) values (?1,?2)",nativeQuery = true)
    void addSongToAlbum(Long idAlbum,Long idTrack);

    @Modifying
    @Transactional
    @Query(value = "delete from album_track where album_id=?1 and track_id=?2",nativeQuery = true)
    void deleteSongFromAlbum(Long idAlbum,Long idTrack);

    @Modifying
    @Transactional
    @Query(value = "update album set status = 1 where id = ?1",nativeQuery = true)
    void updateAlbumStatus(Long idAlbum);
}
