package com.podcast.app.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.podcast.app.dto.PodcastRequest;
import com.podcast.app.dto.PodcastResponse;
import com.podcast.app.exception.ResourceNotFoundException;
import com.podcast.app.model.Category;
import com.podcast.app.model.Podcast;
import com.podcast.app.repository.CategoryRepository;
import com.podcast.app.repository.PodcastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class PodcastServiceImplTest {
    private PodcastRepository podcastRepository;
    private CategoryRepository categoryRepository;
    private PodcastServiceImpl podcastService;

    @BeforeEach
    public void setup() {
        podcastRepository = mock(PodcastRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        podcastService = new PodcastServiceImpl(podcastRepository, categoryRepository);
    }

    @Test
    public void testCreatePodcastSuccessfully() {
        // Arrange
        PodcastRequest request = new PodcastRequest();
        request.setTitle("Test Podcast");
        request.setDescription("Some description");
        request.setImage("cover.jpg");
        request.setCategoryId(1L);

        Category mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setName("Tech");

        Podcast mockPodcast = new Podcast();
        mockPodcast.setId(1L);
        mockPodcast.setTitle(request.getTitle());
        mockPodcast.setDescription(request.getDescription());
        mockPodcast.setImage(request.getImage());
        mockPodcast.setCategory(mockCategory);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));
        when(podcastRepository.save(ArgumentMatchers.any(Podcast.class))).thenReturn(mockPodcast);

        // Act
        PodcastResponse response = podcastService.createPodcast(request);

        // Assert
        assertNotNull(response);
        assertEquals("Test Podcast", response.getTitle());
        verify(podcastRepository, times(1)).save(any(Podcast.class));
    }

    @Test
    public void testGetPodcastById_Success() {
        Podcast podcast = new Podcast();
        podcast.setId(1L);
        podcast.setTitle("Test Podcast");

        when(podcastRepository.findById(1L)).thenReturn(Optional.of(podcast));

        PodcastResponse response = podcastService.getPodcastById(1L);

        assertNotNull(response);
        assertEquals("Test Podcast", response.getTitle());
    }

    @Test
    public void testGetAllPodcasts_WithoutCategory() {
        Pageable pageable = Pageable.ofSize(10);
        Page<Podcast> mockPage = new PageImpl<>(List.of(new Podcast(), new Podcast()));

        when(podcastRepository.findAll(pageable)).thenReturn(mockPage);

        Page<PodcastResponse> result = podcastService.getAllPodcastsByCategoryId(null, pageable);

        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testGetAllPodcasts_WithCategory() {
        Category category = new Category();
        category.setId(1L);

        Pageable pageable = Pageable.ofSize(5);
        Page<Podcast> mockPage = new PageImpl<>(List.of(new Podcast()));

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(podcastRepository.findAllByCategory(category, pageable)).thenReturn(mockPage);

        Page<PodcastResponse> result = podcastService.getAllPodcastsByCategoryId(1L, pageable);

        assertEquals(1, result.getContent().size());
    }

    @Test
    public void testUpdatePodcast_Success() {
        Long id = 1L;

        PodcastRequest request = new PodcastRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Desc");
        request.setImage("img.png");
        request.setCategoryId(2L);

        Category category = new Category();
        category.setId(2L);

        Podcast podcast = new Podcast();
        podcast.setId(id);
        podcast.setTitle("Old Title");

        when(podcastRepository.findById(id)).thenReturn(Optional.of(podcast));
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));
        when(podcastRepository.save(any(Podcast.class))).thenAnswer(i -> i.getArgument(0));

        PodcastResponse response = podcastService.updatePodcast(id, request);

        assertEquals("Updated Title", response.getTitle());
        assertEquals("Updated Desc", response.getDescription());
        assertEquals("img.png", response.getImage());
    }


    @Test
    public void testDeletePodcast_NotFound() {
        when(podcastRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> podcastService.deletePodcastById(99L));
    }


}
