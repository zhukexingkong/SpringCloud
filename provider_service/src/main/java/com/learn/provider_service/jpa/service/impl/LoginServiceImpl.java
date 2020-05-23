package com.learn.provider_service.jpa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.learn.provider_service.jpa.common.BaseMsgResp;
import com.learn.provider_service.jpa.common.MD5Utils;
import com.learn.provider_service.jpa.common.RedisManager;
import com.learn.provider_service.jpa.entity.UserInfo;
import com.learn.provider_service.jpa.service.LoginService;
import com.learn.provider_service.jpa.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserInfoService userService;

    @Override
    public BaseMsgResp login(String username, String password) throws AuthenticationException{
        BaseMsgResp resp = new BaseMsgResp();
        try {
            password = MD5Utils.EncoderByMD5(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        //获取用户
        UserInfo userInfo = userService.findByUsername(username);
        //token
        Serializable sessionId = subject.getSession().getId();
        //将token放入redis
        RedisManager manager = RedisManager.getRedisSingleton();
        manager.set("sys:login:user_token_"+sessionId.toString(),userInfo.getUid()+"",60*30);
        //防止同一个账号同时登录
        manager.set("sys:user:id_"+userInfo.getUid(), sessionId.toString(),60*30);
        //用户信息
        manager.set("sys:login:user_info_"+userInfo.getUid(), JSONObject.toJSONString(userInfo),60*30);
        return resp;
    }
}
