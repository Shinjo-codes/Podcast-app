package com.podcast.app.service.interfaces;

import com.podcast.app.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
        List<CategoryResponse> getAllCategories();
        CategoryResponse getById(Long id);
    }

