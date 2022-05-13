package com.fangshuo.stars.exception;

public enum BusinessExceptionCode {

    USER_NAME_EXIST("用户名已存在"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    VOTE_REPEAT("您已评价过"),
    CIRCLE_NAME_EXIST("兴趣圈名字已存在"),
    JOINED_CIRCLE("'已经加入了此兴趣圈'"),
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
