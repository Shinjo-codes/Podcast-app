package com.podcast.app.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.podcast.app.dto.CategoryResponse;
import com.podcast.app.exception.ResourceNotFoundException;
import com.podcast.app.model.Category;
import com.podcast.app.repository.CategoryRepository;
import com.podcast.app.repository.PodcastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;
    private CategoryServiceImpl categoryService;

    private PodcastRepository podcastRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository, podcastRepository);
    }

    @Test
    void testGetAllCategories() {
        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("Tech");

        Category c2 = new Category();
        c2.setId(2L);
        c2.setName("Health");

        when(categoryRepository.findAll()).thenReturn(List.of(c1, c2));

        List<CategoryResponse> responses = categoryService.getAllCategories();

        assertEquals(2, responses.size());
        assertEquals("Tech", responses.get(0).getName());
    }

    @Test
    void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.getById(99L));
    }

    @Test
    void testGetCategoryById_Success() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Education");
        category.setSlug("education");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        CategoryResponse response = categoryService.getById(1L);

        assertEquals("Education", response.getName());
        assertEquals("education", response.getSlug());
    }
}
