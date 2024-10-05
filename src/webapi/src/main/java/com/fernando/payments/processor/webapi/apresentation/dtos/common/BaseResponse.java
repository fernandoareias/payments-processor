package com.fernando.payments.processor.webapi.apresentation.dtos.common;

import com.fernando.payments.processor.core.cqrs.View;
import lombok.Data;

@Data
public class BaseResponse<V extends View> {

    private int statusCode;
    private String message;
    private V data;

    public BaseResponse(int statusCode, String message, V data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    protected BaseResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }
}
