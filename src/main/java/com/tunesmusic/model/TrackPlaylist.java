package com.tunesmusic.model;
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
@Table(name = "track_playlist")
public class TrackPlaylist {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_playlist")
    private Playlist playlist;


    @ManyToOne
    @JoinColumn(name = "id_track")
    private Track track;

    @Column(name = "day_added")
    private Date dayAdded;

    // Composite key setup (if using JPA 2.0, use @IdClass or @EmbeddedId)

    // Getters and Setters
}