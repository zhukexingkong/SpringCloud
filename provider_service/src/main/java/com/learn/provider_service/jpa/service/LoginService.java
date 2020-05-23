package com.learn.provider_service.jpa.service;

import com.learn.provider_service.jpa.common.BaseMsgResp;
import org.apache.shiro.authc.AuthenticationException;

public interface LoginService {
    public BaseMsgResp login(String username, String password) throws AuthenticationException;
}
