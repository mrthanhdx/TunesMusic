package com.tunesmusic.controller.user;

import com.tunesmusic.model.Artist;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
import com.tunesmusic.service.ArtistService;
import com.tunesmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tunesmusic/artist-following")
public class ArtistFollowingController {

    @Autowired
    UserService userService;
    @Autowired
    ArtistService artistService;


    @PostMapping("/request-follow")
    @ResponseBody
    public String handleRequestFollow(@RequestParam("idArtist") Long idArtist, Authentication authentication) {
        String action = "follow";
        if (authentication != null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            User user1 = userService.findById(user.getId());
            List<Artist> listArtistFollowing = user1.getListArtistFollowing();
            for (Artist artist : listArtistFollowing
            ) {
                if (artist.getId() == idArtist) {
                    action = "unfollow";
                    userService.unfollowArtist(user.getId(), idArtist);
                    return action;
                }
            }
            userService.followArtist(user.getId(), idArtist);
            return action;
        }
        return "error 403";
    }


}
