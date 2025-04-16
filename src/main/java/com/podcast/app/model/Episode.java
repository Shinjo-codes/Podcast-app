package com.podcast.app.model;

import com.podcast.app.model.Episode;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "podcast_id")
    private Podcast podcast;

    @Column(nullable = false)
    private String title;

    private String audioUrl;
    private String duration;

    private Date createdAt;
    private Date updatedAt;
}

