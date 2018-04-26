package com.makshi.web.gateway.web;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {
    private final String SHIRO_LOGIN_EXCEPTION_ATTR = "shiroLoginFailure";
    private Logger log = Logger.getLogger(LoginController.class);

    @RequestMapping({"/login"})
    public String login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception{
        log.info("Do Login!");
        String exception = (String) request.getAttribute(SHIRO_LOGIN_EXCEPTION_ATTR);

        if (!StringUtils.isEmpty(exception)) {
            String msg = "";
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "账号或密码错误！";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "账号或密码错误！";
            } else {
                msg = "其他异常："+exception;
            }
            map.put("msg", msg);
//        }else if(SecurityUtils.getSubject().isAuthenticated()){
//            response.sendRedirect("/platform/console/main");
        }

        return "thymeleaf/login";
    }
}
