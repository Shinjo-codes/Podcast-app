package com.podcast.app.service.interfaces;

import com.podcast.app.dto.PodcastRequest;
import com.podcast.app.dto.PodcastResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PodcastService {
    PodcastResponse createPodcast(PodcastRequest podcastRequest);
    PodcastResponse getPodcastById(Long id);

    Page<PodcastResponse> getAllPodcastsByCategoryId (Long categoryId, Pageable pageable);

    PodcastResponse updatePodcast (Long id, PodcastRequest request);

    void deletePodcastById (Long id);
}
