package com.podcast.app.repository;

import com.podcast.app.model.Category;
import com.podcast.app.model.Podcast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PodcastRepository extends JpaRepository<Podcast, Long> {

    Page<Podcast> findAllByCategory(Category category, Pageable pageable);
}
