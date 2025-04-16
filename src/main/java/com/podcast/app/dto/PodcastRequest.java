package com.podcast.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//-- dto/PodcastRequest.java ---sent by client

@Data
public class PodcastRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String image;

    @NotNull
    private Long categoryId;
}
