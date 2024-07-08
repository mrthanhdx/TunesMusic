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
@Table(name = "post_comment")
public class PostComment {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    @Column(name = "comment")
    private String comment;

    @Column(name = "comment_date")
    private Date commentDate;

    // Composite key setup (if using JPA 2.0, use @IdClass or @EmbeddedId)

    // Getters and Setters
}
