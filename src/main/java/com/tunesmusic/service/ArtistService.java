package com.tunesmusic.service;

import com.tunesmusic.model.Artist;

import java.util.List;

public interface ArtistService extends CommonService<Artist> {
    public List<Artist> findList5Artist();
}