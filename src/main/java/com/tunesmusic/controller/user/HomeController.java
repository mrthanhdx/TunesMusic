package com.tunesmusic.controller.user;

import com.tunesmusic.model.Track;
import com.tunesmusic.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/tunesmusic")
public class HomeController {

    @Autowired
    TrackService trackService;
    @GetMapping("/playsong")
    public String playSong(Model model, @RequestParam("id") Long id) {
        List<Track> trackList = trackService.findTop5Track();
        model.addAttribute("listTrack",trackList);
        Track track = trackService.findById(id);
        model.addAttribute("trackPlay",track);
        return "/user/index";
    }


}
