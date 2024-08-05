package com.tunesmusic.controller.artist;

import com.tunesmusic.model.Track;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
import com.tunesmusic.service.AlbumService;
import com.tunesmusic.service.ArtistService;
import com.tunesmusic.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/artist/tunesmusic")
public class ArtistIndexController {
    @Autowired
    TrackService trackService;

    @Autowired
    AlbumService albumService;

    @Autowired
    ArtistService artistService;
    @GetMapping("")
    public String openArtistHome(Authentication authentication, Model model){
        if (authentication!=null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user",user);
        }
        List<Track> trackList = trackService.findTop5Track();
        model.addAttribute("listTrack",trackList);
        model.addAttribute("listAlbum",albumService.findTop5Album());
        model.addAttribute("listArtistViral",artistService.findList5Artist());
        return "/artist/index";
    }
}
