package com.tunesmusic.service;

import com.tunesmusic.model.Playlist;

import java.util.List;

public interface PlaylistService extends CommonService<Playlist> {
    List<Playlist> findPlaylistByIdUser(Long idUser);

    void addSongToPlaylist(Long playlistId,Long trackId);

    void deleteTrackFromPlaylist(Long playlistId,Long trackId);
}
