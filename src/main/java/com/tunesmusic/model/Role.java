package com.tunesmusic.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

//    @ManyToMany(mappedBy="roles")
//    @JsonIgnore
//    private List<User> users;


//    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
//    private Set<UserRole> userRoles;

    // Getters and Setters
}
