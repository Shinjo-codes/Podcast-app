package com.podcast.app.service.impl;

import com.podcast.app.dto.CategoryResponse;
import com.podcast.app.exception.ResourceNotFoundException;
import com.podcast.app.model.Category;
import com.podcast.app.repository.CategoryRepository;
import com.podcast.app.repository.PodcastRepository;
import com.podcast.app.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final PodcastRepository podcastRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               PodcastRepository podcastRepository) {
        this.categoryRepository = categoryRepository;
        this.podcastRepository = podcastRepository;
    }


    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> {
                    CategoryResponse response = new CategoryResponse();
                    response.setId(category.getId());
                    response.setName(category.getName());
                    response.setSlug(category.getSlug());
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid category"));

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setSlug(category.getSlug());
        categoryResponse.setName(category.getName());

        return categoryResponse;
    }
}
