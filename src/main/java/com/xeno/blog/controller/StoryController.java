package com.xeno.blog.controller;

import com.xeno.blog.dto.LikedUserDto;
import com.xeno.blog.model.Story;
import com.xeno.blog.model.User;
import com.xeno.blog.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/story")
public class StoryController {

    @Autowired
    StoryService storyService;

    @GetMapping("/{id}")
    public ResponseEntity<Story> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(storyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> createStory(@RequestBody Story story){
        storyService.postStory(story);
        return ResponseEntity.ok("Story Created");
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Story>> findByCategory(@PathVariable("categoryId") String catId){
        return ResponseEntity.ok(storyService.findByCategory(catId));
    }

    @GetMapping("/likes/{storyId}")
    public ResponseEntity<List<LikedUserDto>> findLikesById(@PathVariable("storyId") String storyId){
        return ResponseEntity.ok(storyService.findLikesById(storyId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Story>> findStoryByUser(@PathVariable("userId") String userId){
        return ResponseEntity.ok(storyService.findStoryByUser(userId));
    }

    @GetMapping()
    public ResponseEntity<List<Story>> findStoryOfUser(){
        return ResponseEntity.ok(storyService.findStoryOfUser());
    }

}
