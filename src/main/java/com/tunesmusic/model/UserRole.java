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
@Table(name = "user_role")
public class UserRole {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role",referencedColumnName = "id")
    private Role role;



    // Composite key setup (if using JPA 2.0, use @IdClass or @EmbeddedId)

    // Getters and Setters
}
