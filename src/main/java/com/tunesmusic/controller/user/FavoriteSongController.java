package com.tunesmusic.controller.user;

import com.tunesmusic.model.Track;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
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
public class FavoriteSongController {
    @Autowired
    UserService userService;

    @GetMapping("/favorite-song/show-all")
    @ResponseBody
    public List<Track> showAllFavoriteSong(Authentication authentication, Model model) {
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        User user1 = userService.findById(user.getId());
        List<Track> listFavoriteTrack = user1.getListTrackFavorite();
        return listFavoriteTrack;
    }

    @GetMapping("remove-from-favorite")
    @ResponseBody
    public List<Track> removeSongFromFavorite(@RequestParam("trackId") Long trackId,Authentication authentication){
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        userService.removeTrackFromFavorite(user.getId(), trackId);
        User user1 = userService.findById(user.getId());
        List<Track> listFavoriteTrack = user1.getListTrackFavorite();
        return listFavoriteTrack;
    }
}
