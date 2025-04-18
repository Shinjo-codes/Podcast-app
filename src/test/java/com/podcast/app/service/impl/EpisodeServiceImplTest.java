package com.podcast.app.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.podcast.app.dto.EpisodeRequest;
import com.podcast.app.dto.EpisodeResponse;
import com.podcast.app.exception.ResourceNotFoundException;
import com.podcast.app.model.Episode;
import com.podcast.app.model.Podcast;
import com.podcast.app.repository.CategoryRepository;
import com.podcast.app.repository.EpisodeRepository;
import com.podcast.app.repository.PodcastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class EpisodeServiceImplTest {

    private EpisodeRepository episodeRepository;
    private PodcastRepository podcastRepository;
    private EpisodeServiceImpl episodeService;

    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        episodeRepository = mock(EpisodeRepository.class);
        podcastRepository = mock(PodcastRepository.class);
        episodeService = new EpisodeServiceImpl(episodeRepository, podcastRepository, categoryRepository);
    }

    @Test
    void testCreateEpisode() {
        Podcast podcast = new Podcast();
        podcast.setId(1L);

        EpisodeRequest request = new EpisodeRequest();
        request.setTitle("Intro Episode");
        request.setAudioUrl("audio.mp3");
        request.setDuration("12:34");
        request.setPodcastId(1L);

        Episode episode = new Episode();
        episode.setId(1L);
        episode.setTitle("Intro Episode");
        episode.setPodcast(podcast);

        when(podcastRepository.findById(1L)).thenReturn(Optional.of(podcast));
        when(episodeRepository.save(any(Episode.class))).thenReturn(episode);

        EpisodeResponse response = episodeService.create(request);

        assertEquals("Intro Episode", response.getTitle());
    }

    @Test
    void testGetEpisodeById_NotFound() {
        when(episodeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> episodeService.getById(99L));
    }

    @Test
    void testGetEpisodesByPodcastId() {
        Podcast podcast = new Podcast();
        podcast.setId(1L);

        Episode e1 = new Episode();
        e1.setId(1L);
        e1.setTitle("E1");

        Episode e2 = new Episode();
        e2.setId(2L);
        e2.setTitle("E2");

        when(podcastRepository.findById(1L)).thenReturn(Optional.of(podcast));
        when(episodeRepository.findByEpisode(podcast)).thenReturn(List.of(e1, e2));

        List<EpisodeResponse> responses = episodeService.getByPodcastId(1L);

        assertEquals(2, responses.size());
    }

    @Test
    void testDeleteEpisode_NotFound() {
        when(episodeRepository.findById(77L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> episodeService.delete(77L));
    }
}
