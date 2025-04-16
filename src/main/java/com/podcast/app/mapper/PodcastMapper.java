package com.podcast.app.mapper;

import com.podcast.app.model.Podcast;
import com.podcast.app.dto.PodcastRequest;
import com.podcast.app.dto.PodcastResponse;
import com.podcast.app.model.Category;
import com.podcast.app.model.Episode;

import java.util.stream.Collectors;

public class PodcastMapper {

    public static Podcast toEntity(PodcastRequest request, Category category) {
        Podcast podcast = new Podcast();
        podcast.setTitle(request.getTitle());
        podcast.setDescription(request.getDescription());
        podcast.setImage(request.getImage());
        podcast.setCategory(category);
        return podcast;
    }

    public static PodcastResponse toResponse(Podcast podcast) {
        PodcastResponse response = new PodcastResponse();
        response.setId(podcast.getId());
        response.setTitle(podcast.getTitle());
        response.setDescription(podcast.getDescription());
        response.setImage(podcast.getImage());
        response.setCategoryId(podcast.getCategory().getId());
        response.setCreatedAt(podcast.getCreatedAt());
        response.setUpdatedAt(podcast.getUpdatedAt());

        if (podcast.getEpisodes() != null) {
            response.setEpisodes(
                    podcast.getEpisodes()
                            .stream()
                            .map(EpisodeMapper::toResponse)
                            .collect(Collectors.toList())
            );
        }

        return response;
    }
}

