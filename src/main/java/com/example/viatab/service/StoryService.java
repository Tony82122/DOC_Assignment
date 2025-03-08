package com.example.viatab.service;

import com.example.viatab.model.Story;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StoryService {
    private final Map<Long, Story> stories = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    // Initialize with some sample data
    public StoryService() {
        addStory(new Story(null, "New Computer Science Curriculum", "VIA University College introduces a revolutionary computer science curriculum", "IT Department"));
        addStory(new Story(null, "Engineering Workshop Success", "Annual engineering workshop attracts record attendance", "Engineering Department"));
        addStory(new Story(null, "Business Innovation Award", "VIA Business department receives national recognition", "Business Department"));
    }

    public Story addStory(Story story) {
        if (story.getId() == null) {
            story.setId(counter.incrementAndGet());
        }
        stories.put(story.getId(), story);
        return story;
    }

    public Story getStory(Long id) {
        return stories.get(id);
    }

    public List<Story> getAllStories() {
        return new ArrayList<>(stories.values());
    }

    public List<Story> getStoriesByDepartment(String department) {
        return stories.values().stream()
                .filter(story -> story.getDepartment().equalsIgnoreCase(department))
                .toList();
    }

    public Story updateStory(Long id, Story storyDetails) {
        if (stories.containsKey(id)) {
            Story story = stories.get(id);
            story.setTitle(storyDetails.getTitle());
            story.setContent(storyDetails.getContent());
            story.setDepartment(storyDetails.getDepartment());
            return story;
        }
        return null;
    }

    public boolean deleteStory(Long id) {
        return stories.remove(id) != null;
    }
}