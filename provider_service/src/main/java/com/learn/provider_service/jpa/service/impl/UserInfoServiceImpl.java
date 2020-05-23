package com.learn.provider_service.jpa.service.impl;


import com.learn.provider_service.jpa.dao.UserInfoDao;
import com.learn.provider_service.jpa.entity.UserInfo;
import com.learn.provider_service.jpa.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}