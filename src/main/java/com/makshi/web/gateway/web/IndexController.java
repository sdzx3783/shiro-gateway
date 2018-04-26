package com.makshi.web.gateway.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @RequestMapping({"/"})
    public void index(HttpServletResponse response) throws Exception{
        response.sendRedirect("/platform/console/main");
//        return "index";
    }
}
