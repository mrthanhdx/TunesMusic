package com.tunesmusic.controller.artist;

import com.tunesmusic.model.Album;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/artist/tunesmusic")
public class ArtistIndexController {
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
    public String index(Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user", user);
        }
        List<Track> trackList = trackService.findTop5Track();
        model.addAttribute("listTrack", trackList);
        model.addAttribute("listAlbum", albumService.findTop5Album());
        model.addAttribute("listArtistViral", artistService.findList5Artist());
        return "/artist/index";
    }

    @GetMapping("/home")
    public String home() {
        return "redirect:/artist/tunesmusic";
    }

    @GetMapping("/")
    public String home1() {
        return "redirect:/artist/tunesmusic";
    }

    @GetMapping("/search")
    public String openSearchPage(Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user", user);
            List<Playlist> listPlaylist = playlistService.findPlaylistByIdUser(user.getId());
            model.addAttribute("playlists", listPlaylist);

        }
        List<Track> trackList = trackService.findTop5Track();
        model.addAttribute("listTrack", trackList);
        return "/artist/search-page";
    }

    @GetMapping("/artist-following")
    public String openSearchArtistFollowingPage(Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user", user);
            User user1 = userService.findById(user.getId());
            List<Artist> listArtistFollowing = user1.getListArtistFollowing();
            model.addAttribute("listArtistFollowing", listArtistFollowing);
        }
        return "/artist/artist-following";
    }

    @GetMapping("/charts")
    public String openChartsPage(Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user", user);
        }
        List<Track> trackList = trackService.findTop5TrackOrderByDESC();
        model.addAttribute("listTrack", trackList);
        return "/artist/charts-page";
    }

    @GetMapping("/favorite-song")
    public String openFavoritesongPage(Authentication authentication, Model model) {
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        model.addAttribute("user", user);
        User user1 = userService.findById(user.getId());
        List<Track> listFavoriteTrack = user1.getListTrackFavorite();
        List<Track> listFavoriteTrackTop5 = new ArrayList<>();
        if (listFavoriteTrack.size() < 5) {
            model.addAttribute("listFavoriteTrack", listFavoriteTrack);
            return "/artist/favorite-songs";
        } else {
            for (int i = 0; i < 5; i++) {
                listFavoriteTrackTop5.add(listFavoriteTrack.get(i));
            }
            model.addAttribute("listFavoriteTrack", listFavoriteTrackTop5);
            return "/artist/favorite-songs";
        }
    }


    @GetMapping("/index")
    public String indexpage() {
        return "redirect:/artist/tunesmusic";
    }

    @GetMapping("/playlist")
    public String openPlaylistPage(Authentication authentication, Model model) {
        if (authentication != null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user", user);
            List<Playlist> listPlaylist = playlistService.findPlaylistByIdUser(user.getId());
            model.addAttribute("listPlaylist", listPlaylist);
        }

        return "/artist/playlist-page";
    }

    @GetMapping("/artist-profile")
    public String openArtistProfile(Model model, @RequestParam("artistId") Long artistId, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user", user);
            List<Playlist> listPlaylist = playlistService.findPlaylistByIdUser(user.getId());
            model.addAttribute("listPlaylist", listPlaylist);
            for (Playlist playlist : listPlaylist
            ) {
                System.out.println(playlist.getPlaylistName());
            }
        }
        Artist artist = artistService.findById(artistId);
        model.addAttribute("artist", artist);
        return "/artist/artist-profile";
    }

    @GetMapping("/song-management")
    public String openSongManagementPage(Authentication authentication, Model model) {
        if (authentication != null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            model.addAttribute("user", user);
            Artist artist = artistService.getArtistByIdUSer(user.getId());
            model.addAttribute("artist", artist);
        }
        return "/artist/song-management";
    }

    @GetMapping("/album-management")
    public String openAlbumManagementPage(Authentication authentication, Model model) {
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        List<Album> listAlbum = albumService.getListAlbumByIdArtist(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("listAlbum",listAlbum);
        return "/artist/album-management";
    }

    @PostMapping("/new-playlist")
    public String createNewPlaylist(Authentication authentication){

        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user  = customUserDetail.getUser();
        Playlist playlist = new Playlist();
        playlist.setUser(user);
        playlist.setCoverPicture("/img/playlist/defaultImg.jpg");
        playlist.setDayCreated(new Date());
        User user1 = userService.findById(user.getId());
        List<Playlist> listPlaylistUser = user1.getListPlaylist();
        String playlistName = "My Playlist #"+(listPlaylistUser.size()+1);
        playlist.setPlaylistName(playlistName);
        playlistService.save(playlist);
        return "redirect:/tunesmusic/artist/playlist" ;

    }
}
