package com.tunesmusic.controller.user;

import com.tunesmusic.model.Track;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
import com.tunesmusic.service.AlbumService;
import com.tunesmusic.service.ArtistService;
import com.tunesmusic.service.TrackService;
import com.tunesmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tunesmusic")
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    TrackService trackService;

    @Autowired
    AlbumService albumService;

    @Autowired
    ArtistService artistService;

    @GetMapping("/playsong")
    @ResponseBody
    public Track playSong(Model model, @RequestParam("id") Long id) {
        Track track = trackService.findById(id);
        Long currentPlayCount = track.getPlayCount();
        Long newPlayCount = currentPlayCount+2;
        trackService.increasePlayCount(newPlayCount,id);
        System.out.println(newPlayCount);
        return track;
    }

    @GetMapping("/add-to-favorite")
    public String addTrackToFavorite(@RequestParam("trackId") Long trackId, Authentication authentication, Model model) {
        if (authentication != null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            User user1 = userService.findById(user.getId());
            List<Track> listFavoriteTrack = user1.getListTrackFavorite();
            boolean isExist = false;
            if (listFavoriteTrack.isEmpty()) {
                userService.insertFavoriteSong(user.getId(), trackId);
            } else {
                for (Track track : listFavoriteTrack
                ) {
                    if (track.getId() == trackId) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    userService.insertFavoriteSong(user.getId(), trackId);
                }
            }
        }

        return "redirect:/tunesmusic/favorite-song";
    }

}
