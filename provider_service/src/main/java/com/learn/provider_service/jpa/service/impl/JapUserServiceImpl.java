package com.learn.provider_service.jpa.service.impl;

import com.learn.provider_service.jpa.dao.UserDao;
import com.learn.provider_service.jpa.entity.User;
import com.learn.provider_service.jpa.service.JapUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JapUserServiceImpl implements JapUserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
