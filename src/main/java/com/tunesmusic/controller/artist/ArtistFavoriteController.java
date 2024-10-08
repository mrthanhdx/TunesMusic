package com.tunesmusic.controller.artist;

import com.tunesmusic.model.Playlist;
import com.tunesmusic.model.Track;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
import com.tunesmusic.service.AlbumService;
import com.tunesmusic.service.ArtistService;
import com.tunesmusic.service.PlaylistService;
import com.tunesmusic.service.TrackService;
import com.tunesmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/artist/tunesmusic")
public class ArtistFavoriteController {

    @Autowired
    UserService userService;
    @Autowired
    TrackService trackService;

    @Autowired
    AlbumService albumService;

    @Autowired
    ArtistService artistService;

    @Autowired
    PlaylistService playlistService;



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

        return "redirect:/artist/tunesmusic/favorite-song";
    }

    @GetMapping("/playlist/detail")
    public String openPlaylistDetail(Model model, @RequestParam("IdPlaylist") Long idPlaylist,Authentication authentication){
        if (authentication!=null){
            CustomUserDetail customUserDetail =(CustomUserDetail) authentication.getPrincipal();
            User user  = customUserDetail.getUser();
            model.addAttribute(user);
        }
        Playlist playlist = playlistService.findById(idPlaylist);
        model.addAttribute("playlist",playlist);
        List<Track> trackList = playlist.getListTrack();
        model.addAttribute("listTrack",trackList);
        return "/artist/playlist-detail";
    }



}
