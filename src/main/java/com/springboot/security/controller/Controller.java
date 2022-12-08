package com.springboot.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.model.User;
import com.springboot.security.services.UserServices;

// user has AdminRole

@RestController
@RequestMapping("/users")
public class Controller {


    @Autowired
    UserServices userServices;

   
    // get All the users
    @GetMapping("/")
    public List<User> getAllUser(){
        return this.userServices.getAllList();
    }

    // get the single user
//    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/{username}")
    public User getSingleUser(@PathVariable("username") String username){
        System.out.println((username));
        return this.userServices.getUserById(username);
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user){
        return this.userServices.addUser(user);
    }
    
    
}
