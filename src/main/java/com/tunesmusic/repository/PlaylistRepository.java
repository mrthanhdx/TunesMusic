package com.tunesmusic.repository;

import com.tunesmusic.model.Playlist;
import com.tunesmusic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    @Query(value = "select * from playlist where id_user = ?1",nativeQuery = true)
    List<Playlist> findPlaylistsByIdUser(Long idUser);

    @Modifying
    @Transactional
    @Query(value = "insert into playlist_track(playlist_id,track_id) values (?1,?2)",nativeQuery = true)
    void addSongToPlaylist(Long playlistId,Long trackId);
}
