package com.podcast.app.dto;

// --- dto/EpisodeRequest.java ---sent by client/user

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EpisodeRequest {
    @NotBlank
    private String title;

    private String audioUrl; //Optional

    private String duration; //Optional

    @NotNull
    private Long podcastId;
}
