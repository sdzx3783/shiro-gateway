package com.makshi.web.gateway.session.shiro;

import org.apache.shiro.session.Session;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public abstract class TokenSessionStoreTemplate implements ISessionStoreTemplate{
    public abstract void saveToken(Serializable token,Serializable sessionId);

    public abstract void delToken(Serializable token);

    public abstract Serializable getSessionIdByToken(Serializable token);

    public Session getSessionByToken(Serializable token) {
        Serializable sessionId = getSessionIdByToken(token);
        if(StringUtils.isEmpty(sessionId)) return null;

        return get(sessionId);
    }

}
