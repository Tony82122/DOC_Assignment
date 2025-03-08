package com.example.viatab.controller;

import com.example.viatab.model.Story;
import com.example.viatab.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stories")
@CrossOrigin(origins = "*") 
public class StoryController { 

    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

@GetMapping(produces = "text/html")
public String getAllStories() {
    List<Story> stories = storyService.getAllStories();

    StringBuilder htmlBuilder = new StringBuilder();
    htmlBuilder.append("<body bgcolor=\"#9FE2BF\">")
            .append("<h1>Today at VIA</h1>")
            .append("<nav>")
            .append("<a href=\"/stories\">All Stories</a> | ")
            .append("<a href=\"/stories/top\">Top Stories</a>")
            .append("</nav>")
            .append("<section>")
            .append("<h2>All Stories VIA</h2>");

    for (Story story : stories) {
        htmlBuilder.append("<div style=\"margin-bottom: 20px; border: 1px solid #ccc; padding: 10px;\">")
                .append("<h3>").append(story.getTitle()).append("</h3>")
                .append("<p><span style=\"color: blue;\">Department:</span> ").append(story.getDepartment()).append("</p>")
                .append("<p>").append(story.getContent()).append("</p>")
                .append("</div>");
    }

    htmlBuilder.append("</section>")
            .append("</body>");

    return htmlBuilder.toString();
}

@GetMapping(value = "/top", produces = "text/html")
public String topStories() {
    return "<body bgcolor=\"#9FE2BF\">" +
           "<h1>Assignment</h1> " +
           "<nav>" +
           "<a href=\"/stories\">All Stories</a> | " +
           "<a href=\"/stories/top\">Top Stories</a>" +
           "</nav>" +
           "<h2>VIA STUFF.</h2> " +
           "<section> " +
           "<h2>Top Stories</h2> " +
           "<p> discover our <span style=\"color: blue;\">featured stories</span> here...</p> " +
           "</section>" +
           "<p>Featured stories</p> " +
           "<ul> " +
           "<li>Top story 1</li> " +
           "<li>Top story 2</li> " +
           "</ul>" +
           "</body>";
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
