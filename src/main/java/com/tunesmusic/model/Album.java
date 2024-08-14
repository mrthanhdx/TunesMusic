package com.tunesmusic.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "date_release")
    private Date dateRelease;

    @Column(name = "source_cover_photo")
    private String sourceCoverPhoto;


    @Column(name = "status")
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "id_artist")
    @JsonIgnore
    private Artist artist;
    // Getters and Setters

    @ManyToMany
    @JoinTable(
            name = "album_track",
            joinColumns = {@JoinColumn(name = "album_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id",referencedColumnName = "id")})
    List<Track> listTrackAlbum =  new ArrayList<>();
}
