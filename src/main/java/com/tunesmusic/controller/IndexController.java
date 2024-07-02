package com.tunesmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tunesmusic")
public class IndexController {

    @GetMapping("")
    public String index(){
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
