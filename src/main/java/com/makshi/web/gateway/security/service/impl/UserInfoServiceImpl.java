package com.makshi.web.gateway.security.service.impl;

import com.makshi.web.gateway.security.model.LoginUser;
import com.makshi.web.gateway.security.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public LoginUser findByUsername(String username) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(username);
        loginUser.setPassword("1");
        return loginUser;
    }
}
