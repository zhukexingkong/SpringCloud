package com.learn.provider_service.jpa.service;


import com.learn.provider_service.jpa.entity.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}