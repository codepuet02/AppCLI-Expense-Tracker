package com.app.models;

public class Response<T>{
    private Result status;
    private T data ;
    private String txt;

    public Response(Result status, T data , String txt){
        this.status = status;
        this.data = data;
        this.txt = txt;

    }

    public T getData() {
        return data;
    }

    public Result getStatus() {
        return status;
    }

    public String getTxt() {
        return txt;
    }
}
