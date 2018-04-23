package com.makshi.web.gateway.session.shiro;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.UUID;

public class StoringTokenSessionDAO<T extends TokenSessionStoreTemplate> extends StoringSessionDAO<T> {
    public final static String LOGIN_TOKEN = "LOGIN_TOKEN";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable token = generateToken();
        session.setAttribute(LOGIN_TOKEN, token);
        Serializable sessionId = super.doCreate(session);
        getSessionStoreTemplate().saveToken(token, sessionId);
        return sessionId;
    }

    @Override
    public void delete(Session session) {
        Serializable token = (Serializable) session.getAttribute(LOGIN_TOKEN);
        if(token != null) {
            getSessionStoreTemplate().delToken(token);
        }
        super.delete(session);
    }

    public Session getByToken(Serializable token){
        return getSessionStoreTemplate().getSessionByToken(token);
    }

    protected Serializable generateToken(){
        return UUID.randomUUID().toString();
    }
}
