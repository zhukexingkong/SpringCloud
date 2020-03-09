package com.learn.provider_service.jpa.controller;

import com.learn.provider_service.jpa.entity.User;
import com.learn.provider_service.jpa.service.JapUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/jpa/user")
public class JpaUserController {
    @Autowired
    private JapUserService japUserService;

    @RequestMapping("save")
    public void save(){
        User user = new User();
        user.setId(1);
        user.setUserName("first");
        user.setName("第一");
        user.setPassword("123");
        user.setNote("第一创建");
        japUserService.save(user);
        System.out.println("保存成功！");
    }
}
