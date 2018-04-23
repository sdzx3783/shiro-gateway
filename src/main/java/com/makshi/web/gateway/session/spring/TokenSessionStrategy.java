package com.makshi.web.gateway.session.spring;

import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenSessionStrategy implements HttpSessionStrategy {
    private final static CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();

    private final static String TOKEN_NAME = "x-session-token";
    @Override
    public String getRequestedSessionId(HttpServletRequest request) {
        String token = getSessionToken(request);
        if (null != token && !token.equals("")) {
            return token; //token即是session id
        }
        return strategy.getRequestedSessionId(request);
    }

    @Override
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
        strategy.onNewSession(session, request, response);
    }

    @Override
    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
        strategy.onInvalidateSession(request, response);
    }

    private String getSessionToken(HttpServletRequest request) {
        return null != request.getHeader(TOKEN_NAME) ? request.getHeader(TOKEN_NAME) : request.getParameter(TOKEN_NAME);
    }
}
