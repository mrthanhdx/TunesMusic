package com.tunesmusic.controller.user;

import com.tunesmusic.model.Track;
import com.tunesmusic.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
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
    TrackService trackService;
    @GetMapping("/playsong")
    @ResponseBody
    public Track playSong(Model model, @RequestParam("trackid") Long id) {
        Track track = trackService.findById(id);
        return track;
    }

    @GetMapping("/search-song")
    public String searchSong(Model model,@RequestParam("keysearch") String keySearch){
        List<Track> trackList = trackService.findTrackByTrackName(keySearch);
        model.addAttribute("listTrack",trackList);
                return "/user/search-page";
    }
}
