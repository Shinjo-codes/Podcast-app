package com.podcast.app.model;

import com.podcast.app.model.Category;
import com.podcast.app.model.Episode;
import jakarta.persistence.*;
        import lombok.*;
        import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Podcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;

    @OneToMany(mappedBy = "podcast", cascade = CascadeType.ALL)
    private List<Episode> episodes = new ArrayList<>();

    private Date createdAt;
    private Date updatedAt;
}