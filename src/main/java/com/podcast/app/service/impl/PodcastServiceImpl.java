package com.podcast.app.service.impl;

import com.podcast.app.dto.PodcastRequest;
import com.podcast.app.dto.PodcastResponse;
import com.podcast.app.exception.ResourceNotFoundException;
import com.podcast.app.mapper.PodcastMapper;
import com.podcast.app.model.Category;
import com.podcast.app.model.Podcast;
import com.podcast.app.repository.CategoryRepository;
import com.podcast.app.repository.PodcastRepository;
import com.podcast.app.service.interfaces.PodcastService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PodcastServiceImpl implements PodcastService {

    //Constructor-based Injection
    private final PodcastRepository podcastRepository;
    private final CategoryRepository categoryRepository;

    public PodcastServiceImpl(PodcastRepository podcastRepository,
                              CategoryRepository categoryRepository) {
        this.podcastRepository = podcastRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PodcastResponse createPodcast(PodcastRequest podcastRequest) {
        Category category = categoryRepository.findById(podcastRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Podcast podcast = PodcastMapper .toEntity(podcastRequest, category);
        Podcast savedPodcast = podcastRepository.save(podcast);

        return PodcastMapper .toResponse(savedPodcast);

    }

    @Override
    public PodcastResponse getPodcastById(Long id) {
        Podcast podcast = podcastRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Podcast not found"));
        return PodcastMapper.toResponse(podcast);

    }

    @Override
    public Page<PodcastResponse> getAllPodcastsByCategoryId(Long categoryId, Pageable pageable) {
        Page<Podcast> podcastPage;
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException ("Podcast category not found"));
            podcastPage = podcastRepository.findAllByCategory(category, pageable);
        } else {
            podcastPage = podcastRepository.findAll(pageable);
        }

        return podcastPage.map(PodcastMapper::toResponse);//converts Page<Podcast> to Page<PodcastResponse> using the mapper

    }

    @Override
    public PodcastResponse updatePodcast(Long id, PodcastRequest request) {
        Podcast existingPodcast = podcastRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException ("Podcast not found"));

        Category existingCategory = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException ("Category not found"));

        existingPodcast.setTitle(request.getTitle());
        existingPodcast.setDescription(request.getDescription());
        existingPodcast.setImage(request.getImage());

        Podcast updatedPodcast = podcastRepository.save(existingPodcast);

        return PodcastMapper.toResponse(updatedPodcast);
    }

    @Override
    public void deletePodcastById(Long id) {
        Podcast podcast = podcastRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException ("Podcast not found"));
        podcastRepository.delete(podcast);


    }
}
