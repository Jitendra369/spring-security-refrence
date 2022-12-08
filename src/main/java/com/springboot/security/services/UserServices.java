package com.springboot.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.springboot.security.model.User;

@Service
public class UserServices {
//	BasicAuthentica
//		BasicAuthenticationFilter
    
    List<User> list = new ArrayList<>();

    // adding static data to the list 
    public UserServices(){
        this.list.add(new User("jittu","jittu@#123", "jittukadu@gmail.com"));
        this.list.add(new User("pooja","pooja@#123", "poojakadu@gmail.com"));
        this.list.add(new User("neetu","neetu@#123", "neetukadu@gmail.com"));
    }

    // return all the list 
    public List<User> getAllList(){
        return list;
    }
    // get the single user
    public User getUserById(String username){
        return this.list.stream().filter((user)->user.getUserName().equals(username)).findAny().orElse(null);
    }

    // add user to the list 
    public User addUser(User user){
        this.list.add(user);
        // send the added user 
        return user;
    }
}
