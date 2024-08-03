package com.tunesmusic.controller;

import com.tunesmusic.model.Artist;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tunesmusic")
public class IndexController {

    @Autowired
    TrackService trackService;

    @Autowired
    AlbumService albumService;

    @Autowired
    ArtistService artistService;

    @Autowired
    UserService userService;

    @Autowired
    PlaylistService playlistService;

    @GetMapping("")
    public String index(Model model, Authentication authentication){
       if (authentication!=null) {
           CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
           User user = customUserDetail.getUser();
           model.addAttribute("user",user);
       }
        List<Track> trackList = trackService.findTop5Track();
        model.addAttribute("listTrack",trackList);
        model.addAttribute("listAlbum",albumService.findTop5Album());
        model.addAttribute("listArtistViral",artistService.findList5Artist());
        return "/user/index";
    }

    @GetMapping("/home")
    public String home(){
        return "redirect:/tunesmusic";
    }
    @GetMapping("/")
    public String home1(){
        return "redirect:/tunesmusic";
    }
    @GetMapping("/search")
    public String openSearchPage(Model model,Authentication authentication){
        if (authentication!=null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user",user);
            List<Playlist> listPlaylist = playlistService.findPlaylistByIdUser(user.getId());
            model.addAttribute("playlists",listPlaylist);
        }

        List<Track> trackList = trackService.findTop5Track();
        model.addAttribute("listTrack",trackList);
        return "/user/search-page";
    }

    @GetMapping("/artist-following")
    public String openSearchArtistFollowingPage(Model model,Authentication authentication){
        if (authentication!=null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user",user);
            User user1 = userService.findById(user.getId());
            List<Artist> listArtistFollowing =user1.getListArtistFollowing();
            model.addAttribute("listArtistFollowing",listArtistFollowing);
        }
        return "/user/artist-following";
    }

    @GetMapping("/charts")
    public String openChartsPage(Model model,Authentication authentication){
        if (authentication!=null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user",user);
        }
        List<Track> trackList = trackService.findTop5TrackOrderByDESC();
        model.addAttribute("listTrack",trackList);
        return "/user/charts-page";
    }

    @GetMapping("/favorite-song")
    public String openFavoritesongPage(Authentication authentication,Model model){
        CustomUserDetail customUserDetail =(CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        model.addAttribute("user",user);
        User user1 = userService.findById(user.getId());
        List<Track> listFavoriteTrack = user1.getListTrackFavorite();
        List<Track> listFavoriteTrackTop5 = new ArrayList<>();
        if (listFavoriteTrack.size()<5){
            model.addAttribute("listFavoriteTrack",listFavoriteTrack);
            return "/user/favorite-songs";
        } else {
            for (int i=0;i<5;i++){
                listFavoriteTrackTop5.add(listFavoriteTrack.get(i));
            }
            model.addAttribute("listFavoriteTrack",listFavoriteTrackTop5);
            return "/user/favorite-songs";
        }
    }

    @GetMapping("/favorite-song/show-all")
    @ResponseBody
    public List<Track> showAllFavoriteSong(Authentication authentication,Model model){
        CustomUserDetail customUserDetail =(CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        User user1 = userService.findById(user.getId());
        List<Track> listFavoriteTrack = user1.getListTrackFavorite();
        return listFavoriteTrack;
    }
    @GetMapping("/index")
    public String indexpage(){
        return "redirect:/tunesmusic";
    }

    @GetMapping("/playlist")
    public String openPlaylistPage(Authentication authentication,Model model){
        if (authentication!=null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user",user);
            List<Playlist> listPlaylist = playlistService.findPlaylistByIdUser(user.getId());
            model.addAttribute("listPlaylist",listPlaylist);
        }

        return "/user/playlist-page";
    }
}
