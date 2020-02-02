package com.xeno.blog.service;

import com.xeno.blog.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryService {

    @Autowired
    MongoOperations mongoOperations;

    public List<Category> findAll(){
        return mongoOperations.findAll(Category.class);
    }

    public Category insertCategory(Category category){
        return mongoOperations.save(category);
    }
}
