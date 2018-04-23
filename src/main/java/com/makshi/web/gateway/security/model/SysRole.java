package com.makshi.web.gateway.security.model;

import java.util.List;

/**
 * 系统角色
 */
public class SysRole {
    private String id; //主键 uuid
    private String role; //名称
    private String description; //角色描述
    private boolean available = false;
    private List<SysPermission> permissions; //权限列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
