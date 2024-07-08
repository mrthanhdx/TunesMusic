package com.tunesmusic.model;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_artist")
    private Artist artist;

    @Column(name = "track_name", nullable = false)
    private String trackName;

    @Column(name = "description")
    private String description;

    @Column(name = "source_audio")
    private String sourceAudio;

    @Column(name = "source_cover_image")
    private String sourceCoverImage;

    @Column(name = "day_added")
    private Date dayAdded;

    @Column(name = "status")
    private Integer status;

    @Column(name = "play_count")
    private Long playCount;

    // Getters and Setters
}
