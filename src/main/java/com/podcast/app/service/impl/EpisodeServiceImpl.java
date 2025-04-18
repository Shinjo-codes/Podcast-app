package com.podcast.app.service.impl;

import com.podcast.app.dto.EpisodeRequest;
import com.podcast.app.dto.EpisodeResponse;
import com.podcast.app.exception.ResourceNotFoundException;
import com.podcast.app.mapper.EpisodeMapper;
import com.podcast.app.model.Category;
import com.podcast.app.model.Episode;
import com.podcast.app.model.Podcast;
import com.podcast.app.repository.CategoryRepository;
import com.podcast.app.repository.EpisodeRepository;
import com.podcast.app.repository.PodcastRepository;
import com.podcast.app.service.interfaces.EpisodeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final PodcastRepository podcastRepository;

    private final CategoryRepository categoryRepository;

    public EpisodeServiceImpl(EpisodeRepository episodeRepository, PodcastRepository podcastRepository,
                              CategoryRepository categoryRepository) {
        this.episodeRepository = episodeRepository;
        this.podcastRepository = podcastRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
        public EpisodeResponse create(EpisodeRequest request) {
            Podcast podcast = podcastRepository.findById(request.getPodcastId())
                    .orElseThrow(() -> new ResourceNotFoundException("Podcast not found"));

            Episode episode = EpisodeMapper.toEntity(request, podcast);
            Episode saved = episodeRepository.save(episode);
            return EpisodeMapper.toResponse(saved);
        }

        @Override
        public EpisodeResponse getById(Long id) {
            Episode episode = episodeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Episode not found"));
            return EpisodeMapper.toResponse(episode);
        }

        @Override
        public List<EpisodeResponse> getByPodcastId(Long podcastId) {
            Podcast podcast = podcastRepository.findById(podcastId)
                    .orElseThrow(() -> new ResourceNotFoundException("Podcast not found"));

            return episodeRepository.findByEpisode(podcast)
                    .stream()
                    .map(EpisodeMapper::toResponse)
                    .collect(Collectors.toList());
        }

    @Override
    public EpisodeResponse updateEpisode(Long id, EpisodeRequest request) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Episode not found"));

        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));

        episode.setTitle(request.getTitle());
        episode.setAudioUrl(request.getAudioUrl());

        Episode updatedEpisode = episodeRepository.save(episode);

        return EpisodeMapper.toResponse(updatedEpisode);

    }

    @Override
        public void delete(Long id) {
            Episode episode = episodeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Episode not found"));
            episodeRepository.delete(episode);
        }
    }

