package com.tunesmusic.service;

import com.tunesmusic.model.Track;

import java.util.List;

public interface TrackService extends CommonService<Track>{
    List<Track> findTop5Track();
    List<Track> findTrackByTrackName(String keyword);

    List<Track> findTop5TrackOrderByDESC();

    List<Track> findAllTrackOrderByDESC();

    void increasePlayCount(Long playCount,Long idSong);

    List<Track> find5TrackByIdGenre(Long idGenre);



}
