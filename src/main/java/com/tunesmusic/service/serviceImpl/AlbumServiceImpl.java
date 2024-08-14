package com.tunesmusic.service.serviceImpl;

import com.tunesmusic.model.Album;
import com.tunesmusic.repository.AlbumRepository;
import com.tunesmusic.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumRepository albumRepository;
    @Override
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Override
    public Album findById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    public List<Album> findTop5Album(){
        return albumRepository.getList5Album();
    }

    @Override
    public List<Album> getListAlbumByIdArtist(Long idArtist) {
        return albumRepository.getListAlbumByArtistId(idArtist);
    }

    @Override
    public void addTrackToAlbum(Long idAlbum, Long idTrack) {
        albumRepository.addSongToAlbum(idAlbum,idTrack);
    }

    @Override
    public void deleteTrackFromAlbum(Long idAlbum, Long idTrack) {
        albumRepository.deleteSongFromAlbum(idAlbum,idTrack);
    }
}
