package com.tunesmusic.controller;

import com.tunesmusic.model.Track;
import com.tunesmusic.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tunesmusic")
public class IndexController {

    @Autowired
    TrackService trackService;

    @GetMapping("")
    public String index(Model model){
        List<Track> trackList = trackService.findTop5Track();
        model.addAttribute("listTrack",trackList);

        return "/user/index";
    }

    @GetMapping("/home")
    public String home(){
        return "redirect:/tunesmusic";
    }

    @GetMapping("/search")
    public String openSearchPage(){
        return "/user/search-page";
    }

    @GetMapping("/artist-following")
    public String openSearchArtistFollowingPage(){
        return "/user/artist-following";
    }

    @GetMapping("/charts")
    public String openChartsPage(){
        return "/user/charts-page";
    }

    @GetMapping("/favorite-song")
    public String openFavoritesongPage(){
        return "/user/favorite-songs";
    }
    @GetMapping("/index")
    public String indexpage(){
        return "redirect:/tunesmusic";
    }

    @GetMapping("/playlist")
    public String openPlaylistPage(){
        return "/user/playlist-page";
    }
}
