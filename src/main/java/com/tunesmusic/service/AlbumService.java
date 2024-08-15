package com.tunesmusic.service;

import com.tunesmusic.model.Album;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AlbumService extends CommonService<Album>{
    List<Album> findTop5Album();
    List<Album> getListAlbumByIdArtist(Long idArtist);
    void addTrackToAlbum(Long idAlbum,Long idTrack);

    void deleteTrackFromAlbum(Long idAlbum,Long idTrack);

    void updateAlbumStatus(Long idAlbum);

}
