package com.fangshuo.stars.resp;

public class UserLoginResp {
    Long id;
    String username;
    String name;
    String password;
    String avatar;
    String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserLoginResp() {
    }

    @Override
    public String toString() {
        return "UserLoginResp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", token=" + token +
                '}';
    }

    public UserLoginResp(Long id, String username, String name, String password, String avatar, String token) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
        this.token = token;
    }

    public UserLoginResp(Long id, String username, String name, String password, String avatar) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
    }

    public UserLoginResp(Long id, String username, String name, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.avatar = "default.png";
    }

}
