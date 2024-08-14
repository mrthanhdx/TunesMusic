package com.tunesmusic.model;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "track")


//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre;


    public String getFormattedPlayCount() {
        if (this.playCount >= 1_000_000) {
            return String.format("%.1fm streams", this.playCount / 1_000_000.0);
        } else if (this.playCount >= 1_000) {
            return String.format("%dk%d streams", this.playCount / 1_000, (this.playCount % 1_000) / 100);
        } else {
            return this.playCount + " streams";
        }
    }
    // Getters and Setters
}
