package com.tunesmusic.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artist_album")
public class ArtistAlbum {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_artist")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

    // Composite key setup (if using JPA 2.0, use @IdClass or @EmbeddedId)

    // Getters and Setters
}
