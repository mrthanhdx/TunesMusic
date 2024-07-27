package com.tunesmusic.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "cover_picture")
    private String coverPicture;

    @Column(name = "day_created")
    private Date dayCreated;

    @ManyToMany
    @JoinTable(
            name = "playlist_track",
            joinColumns = {@JoinColumn(name = "playlist_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id", referencedColumnName = "id")})
    private List<Track> listTrack = new ArrayList<>();
    // Getters and Setters
}

