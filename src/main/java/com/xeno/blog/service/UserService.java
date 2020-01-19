package com.xeno.blog.service;

import com.xeno.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query searchQuery = new Query(Criteria.where("username").is(username));
        User user = mongoOperations.findOne(searchQuery, User.class);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public User findById(String id){
        return mongoOperations.findById(id, User.class
        );
    }

    public ResponseEntity<String> register(User user){
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        mongoOperations.save(newUser);
        return ResponseEntity.status(200).body("User Created");
    }

    public List<User> getAllUsers() {
        return mongoOperations.findAll(User.class);
    }

    public User getAuthorizeUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Query searchQuery = new Query(Criteria.where("username").is(username));
        return mongoOperations.findOne(searchQuery, User.class);
    }

}
