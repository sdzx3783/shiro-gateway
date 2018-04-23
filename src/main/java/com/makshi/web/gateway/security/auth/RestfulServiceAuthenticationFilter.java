package com.makshi.web.gateway.security.auth;

import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class RestfulServiceAuthenticationFilter extends PassThruAuthenticationFilter{
    private String serviceLoginFailUrl = "/service/loginfail";
    private String serviceLoginUrl = "/service/login";

    public void setServiceLoginFailUrl(String serviceLoginFailUrl) {
        this.serviceLoginFailUrl = serviceLoginFailUrl;
    }

    public String getServiceLoginFailUrl() {
        return this.serviceLoginFailUrl;
    }

    public String getServiceLoginUrl() {
        return serviceLoginUrl;
    }

    public void setServiceLoginUrl(String serviceLoginUrl) {
        this.serviceLoginUrl = serviceLoginUrl;
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, getServiceLoginFailUrl());
    }

    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return pathsMatch(getServiceLoginUrl(), request);
    }
}
