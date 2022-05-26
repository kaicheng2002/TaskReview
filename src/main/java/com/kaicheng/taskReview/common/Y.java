package com.kaicheng.taskReview.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author: Hankaicheng
 * @Date: 2022/5/13 18:56
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Y {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应内容
     */
    private Object data;


    private Y(){

    }

    public static Y getInstance(){
        return new Y();
    }


    public static Y OK(Object data){
        Y y = getInstance();
        y.setCode(200);
        y.setMessage("success");
        y.setData(data);
        return y;
    }

    public static Y OK(String message,Object data){
        Y y = getInstance();
        y.setCode(200);
        y.setMessage(message);
        y.setData(data);
        return y;
    }

    public static Y OK(String message){
        Y y = getInstance();
        y.setCode(200);
        y.setMessage(message);
        return y;
    }

    public static Y OK(){
        Y y = getInstance();
        y.setCode(200);
        y.setMessage("success");
        return y;
    }

    public static Y ERROR(){
        Y y = getInstance();
        y.setCode(500);
        y.setMessage("未知异常，请联系管理员");
        return y;
    }

    public static Y ERROR(String message){
        Y y = getInstance();
        y.setCode(500);
        y.setMessage(message);
        return y;
    }


}
