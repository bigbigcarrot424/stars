package com.fangshuo.stars.exception;

public enum BusinessExceptionCode {

    USER_NAME_EXIST("用户名已存在"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    VOTE_REPEAT("您已点赞过"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
