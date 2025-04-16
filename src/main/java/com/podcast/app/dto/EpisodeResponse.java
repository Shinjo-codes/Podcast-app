package com.podcast.app.dto;

// --- dto/EpisodeResponse.java ---sent to client/user

import lombok.Data;
import java.util.Date;

@Data
public class EpisodeResponse {
    private Long id;
    private String title;
    private String audioUrl;
    private String duration;
    private Long podcastId;
    private Date createdAt;
    private Date updatedAt;
}
