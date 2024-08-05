package com.tunesmusic.model;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "year_activity")
    private Integer yearActivity;

    @Column(name = "follower")
    private Long follower;

    @OneToMany(mappedBy = "artist",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Track> trackList = new ArrayList<>();

    // Getters and Setters
}
