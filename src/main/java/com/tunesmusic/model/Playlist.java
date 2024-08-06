package com.tunesmusic.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlist")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "playlist_name")
    private String playlistName;

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
