package com.podcast.app.mapper;

import com.podcast.app.model.Episode;
import com.podcast.app.model.Podcast;
import com.podcast.app.dto.EpisodeRequest;
import com.podcast.app.dto.EpisodeResponse;

public class EpisodeMapper {

    public static Episode toEntity(EpisodeRequest request, Podcast podcast) {
        Episode episode = new Episode();
        episode.setTitle(request.getTitle());
        episode.setAudioUrl(request.getAudioUrl());
        episode.setDuration(request.getDuration());
        episode.setPodcast(podcast);
        return episode;
    }

    public static EpisodeResponse toResponse(Episode episode) {
        EpisodeResponse response = new EpisodeResponse();
        response.setId(episode.getId());
        response.setTitle(episode.getTitle());
        response.setAudioUrl(episode.getAudioUrl());
        response.setDuration(episode.getDuration());
        response.setPodcastId(episode.getPodcast().getId());
        response.setCreatedAt(episode.getCreatedAt());
        response.setUpdatedAt(episode.getUpdatedAt());
        return response;
    }
}
