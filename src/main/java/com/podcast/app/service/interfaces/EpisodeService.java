package com.podcast.app.service.interfaces;

import com.podcast.app.dto.EpisodeRequest;
import com.podcast.app.dto.EpisodeResponse;

import java.util.List;

public interface EpisodeService {
    EpisodeResponse create(EpisodeRequest request);
    EpisodeResponse getById(Long id);
    List<EpisodeResponse> getByPodcastId(Long podcastId);

    EpisodeResponse updateEpisode(Long id, EpisodeRequest request);
    void delete(Long id);
}

