package com.learn.consumer_service.service.impl;

import com.learn.consumer_service.bean.User;
import com.learn.consumer_service.service.ProviderService;
import org.springframework.stereotype.Component;

@Component
public class ProviderServiceFallBack implements ProviderService {
    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("用户不存在");
        return user;
    }
}
