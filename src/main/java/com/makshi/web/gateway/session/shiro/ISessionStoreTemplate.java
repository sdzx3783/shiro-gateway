package com.makshi.web.gateway.session.shiro;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

public interface ISessionStoreTemplate {
    /**
     * 更新session
     * @param sessionId
     * @param session
     */
    public void set(Serializable sessionId, Session session);

    /**
     * 读取session
     * @param sessionId
     * @return
     */
    public Session get(Serializable sessionId);

    /**
     * 删除session
     * @param sessionId
     */
    public void del(Serializable sessionId);

    /**
     * 通过searchKey模糊查询， 可用于统计活跃的session等。
     * @param searchKey
     * @return
     */
    public Collection<Session> keys(String searchKey);
}
