package util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import config.ResponseBody;

import java.io.Serializable;
import java.util.Map;

public class GlobalResponse<T> implements Serializable{
    private ResponseBody<T> body = new ResponseBody<T>();// 响应体
    private Map<String, String> header = Maps.newHashMap();// 响应头

    {
        header.put("code", "1");
    }

    private GlobalResponse() {

    }

    private GlobalResponse(T data) {
        this.body.setData(data);
    }

    private GlobalResponse(int code, String msg) {
        this.body.setCode(code);
        this.body.setMsg(msg);
    }

    private GlobalResponse(int code, String msg, T data) {
        this.body.setCode(code);
        this.body.setMsg(msg);
        this.body.setData(data);
    }

    public ResponseBody<T> getBody() {
        return body;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    // 使之不在 json 序列化结果当中
    @JsonIgnore
    public boolean isSuccess() {
        // 将表示成功的响应码统一为 200
        return this.body.getCode() == 200;
    }

    @JsonIgnore
    public int getCode() {
        return this.body.getCode();
    }

    @JsonIgnore
    public T getData() {
        return this.body.getData();
    }

    @JsonIgnore
    public String getMsg() {
        return this.body.getMsg();
    }

    public static <T> GlobalResponse<T> create(int code, String msg, T data) {
        return new GlobalResponse<T>(code, msg, data);
    }

    public static <T> GlobalResponse<T> createBySuccess(T data) {
        return new GlobalResponse<T>(data);
    }

    public static <T> GlobalResponse<T> createBySuccess() {
        return new GlobalResponse<T>();
    }

    public static <T> GlobalResponse<T> createByError(int code, String msg) {
        return new GlobalResponse<T>(code, msg);
    }
}
