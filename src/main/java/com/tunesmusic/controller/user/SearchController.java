package com.tunesmusic.controller.user;

import com.tunesmusic.model.Playlist;
import com.tunesmusic.model.Track;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
import com.tunesmusic.service.PlaylistService;
import com.tunesmusic.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tunesmusic/search")
public class SearchController {

    @Autowired
    PlaylistService playlistService;

    @Autowired
    TrackService trackService;
    @GetMapping("/playsong")
    @ResponseBody
    public Track playSong(Model model, @RequestParam("trackid") Long id) {
        Track track = trackService.findById(id);
        return track;
    }

    @GetMapping("/search-song")
    public String searchSong(Model model, @RequestParam("keysearch") String keySearch, Authentication authentication){
        if (authentication!=null) {
            CustomUserDetail customUserDetail =(CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user",user);
            List<Playlist> listPlaylist = playlistService.findPlaylistByIdUser(user.getId());
            model.addAttribute("playlists",listPlaylist);
        }
        List<Track> trackList = trackService.findTrackByTrackName(keySearch);
        model.addAttribute("listTrack",trackList);
                return "/user/search-page";
    }


}
