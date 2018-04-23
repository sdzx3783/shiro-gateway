package com.makshi.web.gateway.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public class LoginService {
    @RequestMapping(value = "/login",consumes = "application/json; charset=utf-8")
    @ResponseBody
    public String login(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            // login failed
            return "login failed";
        }

        if (subject.isAuthenticated()) {
            return "login sucess!";
        } else {
            return "login failed 2";
        }

    }

    @RequestMapping("/loginfail")
    @ResponseBody
    public String loginFail() {
        return "loginfail";
    }
}

