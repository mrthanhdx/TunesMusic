package com.tunesmusic.controller.artist;

import com.tunesmusic.model.Album;
import com.tunesmusic.model.Artist;
import com.tunesmusic.model.Track;
import com.tunesmusic.model.User;
import com.tunesmusic.security.CustomUserDetail;
import com.tunesmusic.service.AlbumService;
import com.tunesmusic.service.ArtistService;
import com.tunesmusic.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/artist/tunesmusic/album-management")
public class AlbumManagementController {

    @Autowired
    AlbumService albumService;

    @Autowired
    StorageService storageService;

    @Autowired
    ArtistService artistService;


    @GetMapping("/manage-album")
    public String openPageManageAlbum(@RequestParam("idAlbum") Long idAlbum, Model model, Authentication authentication) {
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        model.addAttribute("user", user);
        Artist artist = artistService.getArtistByIdUSer(user.getId());
        Album album = albumService.findById(idAlbum);
        List<Track> listTrackExistInAlbum = album.getListTrackAlbum();
        List<Track> listTrackValidToAdd = new ArrayList<>(artist.getTrackList());
        listTrackValidToAdd.removeAll(listTrackExistInAlbum);
        model.addAttribute("listTrackValidToAdd", listTrackValidToAdd);
        model.addAttribute("albumDetail", album);
        return "/artist/album-detail";
    }

    @GetMapping("/add-song-to-album")
    @ResponseBody
    public List<Track> addSongToAlbum(@RequestParam("idAlbum") Long idAlbum,
                                      @RequestParam("idSong") Long idTrack) {
        albumService.addTrackToAlbum(idAlbum, idTrack);
        Album album = albumService.findById(idAlbum);
        return album.getListTrackAlbum();
    }

    @GetMapping("/remove-song-from-album")
    @ResponseBody
    public List<Track> removeSongFromAlbum(@RequestParam("idAlbum") Long idAlbum,
                                      @RequestParam("idSong") Long idTrack) {
        albumService.deleteTrackFromAlbum(idAlbum, idTrack);
        Album album = albumService.findById(idAlbum);
        return album.getListTrackAlbum();
    }

    @PostMapping("/create-new-album")
    public String createNewAlbum(@RequestParam("albumName") String albumName,
                                 @RequestParam("albumDescription") String albumDescription,
                                 @RequestParam("sourceCoverImage") MultipartFile sourceCoverImage,
                                 Authentication authentication,
                                 RedirectAttributes redirectAttributes) {
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        Artist artist = artistService.getArtistByIdUSer(user.getId());
        String sourceCoverImg = "/img/album/" + storageService.storeImageAlbum(sourceCoverImage);
        Album album = new Album();
        album.setTitle(albumName);
        album.setIntroduce(albumDescription);
        album.setSourceCoverPhoto(sourceCoverImg);
        album.setArtist(artist);
        album.setStatus(0);
        albumService.save(album);
        redirectAttributes.addFlashAttribute("isSuccess", true);
        redirectAttributes.addFlashAttribute("message", "create new album successfully !");
        return "redirect:/artist/tunesmusic/album-management";
    }


}
