package com.tunesmusic.model;

import jakarta.persistence.*;

import java.util.Date;

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

    // Getters and Setters
}

