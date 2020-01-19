package com.xeno.blog.service;

import com.xeno.blog.dto.LikedUserDto;
import com.xeno.blog.model.Category;
import com.xeno.blog.model.Story;
import com.xeno.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoryService {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    UserService userService;

    public Story findById(String id){
        return mongoOperations.findById(id,Story.class);
    }

    public void postStory(Story story) {
        mongoOperations.save(story);
    }

    public List<Story> findByCategory(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").is(id));
        return mongoOperations.find(query, Story.class);
    }

    public List<LikedUserDto> findLikesById(String id) {
        Story story = mongoOperations.findById(id, Story.class);
        List<LikedUserDto> users = new ArrayList<>();
        story.getLikes().forEach(likedUser->{
            User user = userService.findById(likedUser);
            users.add(new LikedUserDto(
                    user.getId(),
                    user.getFirstName()+" "+user.getLastName()
                   )
            );
        });
        return users;
    }

    public List<Story> findStoryByUser(String userId) {
        System.out.println("UserId"+userId);
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoOperations.find(query, Story.class);
    }

    public List<Story> findStoryOfUser() {
        User user = userService.getAuthorizeUser();
        return findStoryByUser(user.getId());
    }
}

