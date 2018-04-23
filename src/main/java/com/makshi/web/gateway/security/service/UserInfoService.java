package com.makshi.web.gateway.security.service;

import com.makshi.web.gateway.security.model.LoginUser;

public interface UserInfoService {
    public LoginUser findByUsername(String username);
}
