package com.podcast.app.controller;

import com.podcast.app.dto.PodcastRequest;
import com.podcast.app.dto.PodcastResponse;
import com.podcast.app.service.interfaces.PodcastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/podcast")
@Tag(name = "Podcast", description = "Important endpoints for managing podcasts")
public class PodcastController {

    @Autowired
    private PodcastService podcastService;

    @Operation(summary= "Creates a new podcast", responses = {@ApiResponse(responseCode = "200",
    description = "Request to create podcast is successful!")})
    @PostMapping
    public ResponseEntity <PodcastResponse> createPodcast (@Valid @RequestBody PodcastRequest podcastRequest) {
    return ResponseEntity.ok(podcastService.createPodcast(podcastRequest));

    }

    @Operation(summary= "Get podcast by Id")
    @GetMapping("/{id}")
    public ResponseEntity <PodcastResponse> getPodcastById (@PathVariable Long id) {
        return ResponseEntity.ok(podcastService.getPodcastById(id));
    }

    @Operation(summary= "Get all available podcast by categoryId")
    @GetMapping
    public ResponseEntity <Page<PodcastResponse>> getAllPodcasts (@RequestParam (required = false)
                                                                  Long categoryId, Pageable pageable) {
        return ResponseEntity.ok(podcastService.getAllPodcastsByCategoryId(categoryId, pageable));
    }

    @Operation(summary= "Update podcast by Id")
    @PutMapping ("/{id}")
    public ResponseEntity<PodcastResponse> updatePodcast (@PathVariable Long id,
                                                          @Valid @RequestBody PodcastRequest podcastRequest) {
        return ResponseEntity.ok(podcastService.updatePodcast(id, podcastRequest));
    }

    @Operation(summary= "Delete podcast by Id")
    @DeleteMapping ("/{id}")
    public ResponseEntity<PodcastResponse> deletePodcast (@PathVariable Long id) {
        podcastService.deletePodcastById(id);

        return ResponseEntity.noContent().build();
    }

}
