package com.tunesmusic.controller.artist;

import com.tunesmusic.model.Artist;
import com.tunesmusic.model.Track;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
import com.tunesmusic.service.ArtistService;
import com.tunesmusic.service.StorageService;
import com.tunesmusic.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/artist/tunesmusic/song-management")
public class SongManagementController {
    @Autowired
    TrackService trackService;

    @Autowired
    ArtistService artistService;

    @Autowired
    StorageService storageService;

    @PostMapping("/create-new-song")
    @ResponseBody
    public List<Track> createNewSong(@RequestParam("trackName") String trackName,
                                     @RequestParam("sourceCoverImage")MultipartFile imageFile,
                                     @RequestParam("sourceAudio") MultipartFile audioFile,
                                     @RequestParam("trackDescription") String trackDescription,
                                     Authentication authentication){
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        Artist artist = artistService.getArtistByIdUSer(user.getId());
        String audiotrackDir = "/audio/"+storageService.store(audioFile);
        String imgTrackDir  = "/img/"+storageService.store(imageFile);
        Track track = new Track();
        track.setArtist(artist);
        track.setTrackName(trackName);
        track.setDescription(trackDescription);
        track.setPlayCount(Long.valueOf(0));
        track.setStatus(1);
        track.setDayAdded(new Date());
        track.setSourceCoverImage(imgTrackDir);
        track.setSourceAudio(audiotrackDir);
        trackService.save(track);
        Artist artist1 = artistService.getArtistByIdUSer(user.getId());
        return artist1.getTrackList();
    }


}
