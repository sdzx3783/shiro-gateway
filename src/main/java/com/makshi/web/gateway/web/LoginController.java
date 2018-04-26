package com.makshi.web.gateway.web;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController extends BaseController{
    private final String SHIRO_LOGIN_EXCEPTION_ATTR = "shiroLoginFailure";
    private Logger log = Logger.getLogger(LoginController.class);

    @RequestMapping({"/login"})
    public String login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception{
        log.info("Do Login!");
        String exception = (String) request.getAttribute(SHIRO_LOGIN_EXCEPTION_ATTR);

        if (!StringUtils.isEmpty(exception)) {
            String msg = "";
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = getMessage("auth.login.fail.unknowaccount", null);
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = getMessage("auth.login.fail.incorrectcredentials", null);
            } else {
                msg = getMessage("auth.login.fail.other", null)+exception;
            }
            map.put("msg", msg);
        }

        return "login";
    }
}
