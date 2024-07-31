package com.tunesmusic.controller.user;

import com.tunesmusic.model.Playlist;
import com.tunesmusic.model.Track;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
import com.tunesmusic.service.PlaylistService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tunesmusic")
public class PlaylistController {

    @Autowired
    UserService userService;
    @Autowired
    PlaylistService playlistService;

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
        return "/user/playlist-detail";
    }
//    @GetMapping("/add-to-playlist")
//    public String
    @PostMapping("/new-playlist")
    public String createNewPlaylist(Authentication authentication){

            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user  = customUserDetail.getUser();
            Playlist playlist = new Playlist();
            playlist.setUser(user);
            playlist.setCoverPicture("/img/playlist/defaultImg.jpg");
            playlist.setDayCreated(new Date());
            playlistService.save(playlist);
            return "redirect:/tunesmusic/playlist" ;

    }



}
