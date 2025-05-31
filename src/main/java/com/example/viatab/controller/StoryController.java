package com.example.viatab.controller;

import com.example.viatab.model.Story;
import com.example.viatab.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stories")
@CrossOrigin(origins = "http://localhost:3000")
public class StoryController {

    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStories() {
        List<Story> stories = storyService.getAllStories();
        Map<String, Object> response = Map.of(
            "title", "Today at VIA",
            "navLinks", List.of(
                Map.of("text", "All Stories", "href", "/stories"),
                Map.of("text", "Top Stories", "href", "/stories/top")
            ),
            "pageTitle", "All Stories VIA",
            "stories", stories
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top")
    public ResponseEntity<Map<String, Object>> getTopStories() {
        List<Story> topStories = storyService.getTopStories();
        Map<String, Object> response = Map.of(
            "title", "Assignment",
            "navLinks", List.of(
                Map.of("text", "All Stories", "href", "/stories"),
                Map.of("text", "Top Stories", "href", "/stories/top")
            ),
            "pageTitle", "VIA STUFF",
            "sectionTitle", "Top Stories",
            "sectionContent", "Discover our featured stories here...",
            "featuredStories", topStories
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Story> getStoryById(@PathVariable Long id) {
        Story story = storyService.getStory(id);
        if (story != null) {
            return ResponseEntity.ok(story);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Story>> getStoriesByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(storyService.getStoriesByDepartment(department));
    }

    @PostMapping
    public ResponseEntity<Story> createStory(@RequestBody Story story) {
        return new ResponseEntity<>(storyService.addStory(story), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Story> updateStory(@PathVariable Long id, @RequestBody Story storyDetails) {
        Story updatedStory = storyService.updateStory(id, storyDetails);
        if (updatedStory != null) {
            return ResponseEntity.ok(updatedStory);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {
        if (storyService.deleteStory(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}