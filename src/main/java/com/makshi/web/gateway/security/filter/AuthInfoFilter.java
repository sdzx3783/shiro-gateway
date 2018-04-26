package com.makshi.web.gateway.security.filter;

import com.makshi.web.gateway.security.model.LoginUser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;

public class AuthInfoFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String account = loginUser==null?null:loginUser.getUsername();

            if(StringUtils.isNotBlank(account)) {
                RequestContext requestContext = RequestContext.getCurrentContext();
                String url = requestContext.getRequest().getRequestURL().toString();
                requestContext.addZuulRequestHeader("x-auth-account", account);
            }
        }
        return null;
    }
}
