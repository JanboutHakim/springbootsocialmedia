package com.example.socialWeb.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "profile_picture_path")
    private String profilePicturePath;
    @OneToMany(mappedBy = "user")
    private Set<Posts> posts;
    @OneToMany(mappedBy = "userReplay")
    private Set<Replies> replies;

    public User(Integer userId) {
        this.userId = userId;
    }
}
