package com.zsmart.accountingProject.ws.rest.vo;

import java.util.List;

public class AuthResponse {
    private String token;
    private String id;
    private String name;
    private String email;
    private List<RoleVo> roles;
    private int societe;

    public AuthResponse(String token, String id, String name, String email, List<RoleVo> roles, int societe) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.societe = societe;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSociete() {
        return societe;
    }

    public void setSociete(int societe) {
        this.societe = societe;
    }

    public List<RoleVo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVo> roles) {
        this.roles = roles;
    }
}
