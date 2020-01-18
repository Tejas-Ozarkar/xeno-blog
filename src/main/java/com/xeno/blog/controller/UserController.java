package com.xeno.blog.controller;

import com.xeno.blog.model.User;
import com.xeno.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public void getUser(){
        userService.postUser();
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody User user){
        return userService.register(user);
    }
}
