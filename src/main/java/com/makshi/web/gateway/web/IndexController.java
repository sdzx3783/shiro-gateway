package com.makshi.web.gateway.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @RequestMapping({"/"})
    public void index(HttpServletResponse response) throws Exception{
        response.sendRedirect("/platform/console/main");
    }
}
