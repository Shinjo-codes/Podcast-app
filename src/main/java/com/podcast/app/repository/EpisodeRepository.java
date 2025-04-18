package com.podcast.app.repository;

import com.podcast.app.model.Episode;
import com.podcast.app.model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

    List<Episode> findByEpisode(Podcast podcast);
}
