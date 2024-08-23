package com.tunesmusic.controller.user;

import com.tunesmusic.model.Artist;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
import com.tunesmusic.service.ArtistService;
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

    @GetMapping("/popular-artist")
    @ResponseBody
    public List<Artist> getListArtistPopular(Authentication authentication){
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        User user1 = userService.findById(user.getId());
        List<Artist> listAllArtist = artistService.findAll();
        List<Artist> listArtistFollowing = user1.getListArtistFollowing();
      listAllArtist.removeAll(listArtistFollowing);
        for (int i = 0; i < listAllArtist.size(); i++) {
            Artist artist = listAllArtist.get(i);
            System.out.println("Artist " + i + ": " + artist.getId() + ", " + artist.getArtistName());
            if (artist == null) {
                System.out.println("Null artist at index " + i);
            }
        }
        return listAllArtist;
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Artist> searchArtist(@RequestParam("artistName") String artistName,Authentication authentication,
                                Model model){
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        User user1 = userService.findById(user.getId());
        List<Artist> listArtistFollowing = user1.getListArtistFollowing();
        List<Artist> listAllArtist = artistService.findArtistByArtistName(artistName);
        for (Artist artist: listAllArtist
             ) {
            boolean isFollowing = listArtistFollowing.contains(artist);
            artist.setIsFollowing(isFollowing);
        }

       return listAllArtist;

    }

}
