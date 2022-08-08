package com.kevinavy.authenticationcenter.constant.enums;

public enum ResponseCode {
    SUCCESS(true,200, "请求成功"),
    LOGOUT(true,201, "登出成功"),
    ERROR(false,400, "未知错误"),
    UNAUTHORIZED(false, 401, "用户认证失败"),
    FORBIDDEN(false, 402, "用户权限不足");


    private final Boolean success;
    private final Integer code;
    private final String msg;

    ResponseCode(Boolean success ,Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
