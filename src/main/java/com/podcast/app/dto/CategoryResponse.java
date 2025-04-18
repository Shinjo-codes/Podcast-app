package com.podcast.app.dto;

import com.podcast.app.model.Podcast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<PodcastResponse> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastResponse> podcasts) {
        this.podcasts = podcasts;
    }

    private String slug;
    private List<com.podcast.app.dto.PodcastResponse> podcasts;
}
