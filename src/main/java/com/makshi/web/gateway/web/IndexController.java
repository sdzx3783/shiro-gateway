package com.makshi.web.gateway.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/","/index"})
    public String index(){
        Subject subject = SecurityUtils.getSubject();

        return "index";
    }
}
