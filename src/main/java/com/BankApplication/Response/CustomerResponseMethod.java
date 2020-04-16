package com.BankApplication.Response;

public class CustomerResponseMethod<ResponseData> {

    private int statusCode;
    private String message;
    private ResponseData responseData;

    public CustomerResponseMethod(int statusCode, String message, ResponseData responseData) {
        this.statusCode = statusCode;
        this.message = message;
        this.responseData = responseData;
    }


    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
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
}
