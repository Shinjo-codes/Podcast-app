package com.podcast.app.dto;

// --- dto/PodcastResponse.java ---sent to client

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class PodcastResponse {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Long categoryId;
    private Date createdAt;
    private Date updatedAt;
    private List<com.podcast.app.dto.EpisodeResponse> episodes;
}
