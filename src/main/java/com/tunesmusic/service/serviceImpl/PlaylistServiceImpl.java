package com.tunesmusic.service.serviceImpl;

import com.tunesmusic.model.Playlist;
import com.tunesmusic.repository.PlaylistRepository;
import com.tunesmusic.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    PlaylistRepository playlistRepository;
    @Override
    public void save(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public Playlist findById(Long id) {
        return playlistRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public List<Playlist> findPlaylistByIdUser(Long idUser) {
        return playlistRepository.findPlaylistsByIdUser(idUser);
    }

    @Override
    public void addSongToPlaylist(Long playlistId, Long trackId) {
        playlistRepository.addSongToPlaylist(playlistId,trackId);
    }
}
