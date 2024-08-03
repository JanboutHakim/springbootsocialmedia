package com.example.socialWeb.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Replies")
public class Replies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Posts post;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userReplay;


}
