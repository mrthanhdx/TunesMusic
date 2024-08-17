package com.tunesmusic.service.serviceImpl;

import com.tunesmusic.model.Track;
import com.tunesmusic.repository.TrackRepository;
import com.tunesmusic.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    TrackRepository trackRepository;
    @Override
    public void save(Track track) {
        trackRepository.save(track);
    }

    @Override
    public Track findById(Long id) {
        Track track = trackRepository.findById(id).orElse(null);
        return track;
    }

    @Override
    public void deleteById(Long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public List<Track> findTop5Track() {
        return trackRepository.findTop5Track();
    }

    @Override
    public List<Track> findTrackByTrackName(String keyword) {
        return trackRepository.findTrackByTrackName(keyword);
    }

    @Override
    public List<Track> findTop5TrackOrderByDESC() {
        return trackRepository.findTop5TrackOrderByDesc();
    }

    @Override
    public List<Track> findAllTrackOrderByDESC() {
        return trackRepository.findAllTrackOrderByDesc();
    }

    @Override
    public void increasePlayCount(Long playCount, Long idSong) {
        trackRepository.increasePlayCount(playCount,idSong);
    }

    @Override
    public List<Track> find5TrackByIdGenre(Long idGenre) {
        return trackRepository.find5TrackByIdGenre(idGenre);
    }
}
