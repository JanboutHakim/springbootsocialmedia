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
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String text;
    private String subject;
    private int up;
    private int down;
    @OneToMany(mappedBy = "post")
    private Set<Replies> replies;

    public Posts(Long postId) {
        this.postId = postId;
    }
}
