package com.tunesmusic.model;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artist")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @Transient
    private Boolean isFollowing;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private List<Track> trackList = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id); // compare by ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    // Getters and Setters
}
