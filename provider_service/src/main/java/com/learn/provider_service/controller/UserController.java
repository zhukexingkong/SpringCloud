package com.learn.provider_service.controller;

import com.learn.provider_service.entity.User;
import com.learn.provider_service.service.UserService;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static Logger logger = LogManager.getLogger(UserController.class);

    @PostMapping("/save")
    public void save(User user){
        userService.save(user);
    }

    @PostMapping("/delete")
    public void delete(Integer id){
        userService.delete(id);
    }

    @PostMapping("/update")
    public void update(User user){
        userService.update(user);
    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findById")
    public User findById(Integer id){
        return userService.findById(id);
    }
}
