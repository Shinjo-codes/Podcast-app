package com.podcast.app.controller;

import com.podcast.app.dto.EpisodeRequest;
import com.podcast.app.dto.EpisodeResponse;
import com.podcast.app.service.interfaces.EpisodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/episode")
@Tag(name = "Episode", description = "Important endpoints for managing podcast episodes")
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @Operation(summary= "Creates a new podcast episode", responses = {@ApiResponse(responseCode = "200",
            description = "Request to create podcast episode is successful!")})
    @PostMapping
    public ResponseEntity<EpisodeResponse> createEpisode (@Valid @RequestBody EpisodeRequest request) {
        return ResponseEntity.ok(episodeService.create(request));
    }

    @Operation(summary= "Get podcast episode by Id")
    @GetMapping("/{id}")
    public ResponseEntity<EpisodeResponse> getEpisodeById(@PathVariable Long id) {
        return ResponseEntity.ok(episodeService.getById(id));
    }

    @Operation(summary= "Get podcast episode by the podcast Id")
    @GetMapping("/podcast/{podcastId}")
    public ResponseEntity<List<EpisodeResponse>> getEpisodesByPodcast(@PathVariable Long podcastId) {
        return ResponseEntity.ok(episodeService.getByPodcastId(podcastId));
    }

    @Operation(summary= "Update podcast episode by Id")
    @PutMapping("/{id}")
    public ResponseEntity<EpisodeResponse> updateEpisode (@PathVariable Long id,
    @Valid @RequestBody EpisodeRequest request) {
        return ResponseEntity.ok(episodeService.updateEpisode(id, request));
    }


    @Operation(summary= "Delete podcast episode by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable Long id) {
        episodeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
