package com.tunesmusic.service.serviceImpl;

import com.tunesmusic.model.Artist;
import com.tunesmusic.repository.ArtistRepository;
import com.tunesmusic.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    ArtistRepository artistRepository;
    @Override
    public List<Artist> findList5Artist() {
        return artistRepository.getList5Artist();
    }

    @Override
    public void save(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        artistRepository.deleteById(id);
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }
}
