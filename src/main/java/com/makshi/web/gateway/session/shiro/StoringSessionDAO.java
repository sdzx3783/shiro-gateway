package com.makshi.web.gateway.session.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

public class StoringSessionDAO<T extends ISessionStoreTemplate> extends AbstractSessionDAO{

    @Resource
    private T sessionStoreTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        sessionStoreTemplate.set(sessionId, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if(sessionId == null) return  null;
        return sessionStoreTemplate.get(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(session == null || session.getId() == null){
            return;
        }

        sessionStoreTemplate.set(session.getId(), session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }

        sessionStoreTemplate.del(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return sessionStoreTemplate.keys("*");
    }

    public T getSessionStoreTemplate() {
        return sessionStoreTemplate;
    }

    public void setSessionStoreTemplate(T sessionStoreTemplate) {
        this.sessionStoreTemplate = sessionStoreTemplate;
    }
}
