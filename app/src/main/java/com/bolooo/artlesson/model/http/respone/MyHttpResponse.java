package com.bolooo.artlesson.model.http.respone;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-16
 * DES : ${}
 * =======================================
 */

public class MyHttpResponse<T> {
    private boolean IsSuccess;
    private T Data;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
