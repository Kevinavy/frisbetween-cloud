package com.kevinavy.authenticationcenter.model.common;


import com.kevinavy.authenticationcenter.constant.enums.ResponseCode;

public class Response {
    private Boolean success;
    private Integer code;
    private String msg;
    private Object data;

    public Response() {

    }

    private Response(ResponseCode responseCode) {
        this.success = responseCode.getSuccess();
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    private Response(ResponseCode responseCode, String msg) {
        this.success = responseCode.getSuccess();
        this.code = responseCode.getCode();
        this.msg = msg;
    }

    private Response(ResponseCode responseCode, Object data) {
        this.success = responseCode.getSuccess();
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

    public Response(ResponseCode responseCode, String msg, Object data) {
        this.success = responseCode.getSuccess();
        this.code = responseCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    public static Response success() {
        return new Response(ResponseCode.SUCCESS);
    }

    public static Response success(Object data) {
        return new Response(ResponseCode.SUCCESS, data);
    }

    public static Response success(String successMsg, Object data) {
        return new Response(ResponseCode.SUCCESS, successMsg, data);
    }

    public static Response error() {
        return new Response(ResponseCode.ERROR);
    }

    public static Response error(String errorMsg) {
        return new Response(ResponseCode.ERROR, errorMsg);
    }

    public static Response error(ResponseCode responseCode) {
        return new Response(responseCode);
    }

    public static Response error(ResponseCode responseCode, String errorMsg) {
        return new Response(responseCode, errorMsg);
    }

    public static Response common(ResponseCode responseCode) {
        return new Response(responseCode);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
