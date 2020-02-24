package com.learn.provider_service.controller;

import com.learn.provider_service.bean.User;
import com.learn.provider_service.mappers.UserMapper;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class HelloController {
    @Autowired
    private UserMapper userMapper;

    private static Logger logger = LogManager.getLogger(HelloController.class);

    @PostMapping("/save")
    public void save(User user){
        userMapper.save(user);
    }

    @PostMapping("/delete")
    public void delete(Integer id){
        userMapper.delete(id);
    }

    @PostMapping("/update")
    public void update(User user){
        userMapper.update(user);
    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        logger.info("findAll");
        logger.error("findAll");
        logger.debug("findAll");
        return userMapper.findAll();
    }

    @GetMapping("/findById")
    public User findById(Integer id){
        return userMapper.findById(id);
    }
}
