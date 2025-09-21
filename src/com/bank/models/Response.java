package com.bank.models;

public class Response {
    private String message;
    private boolean success;

    public Response(boolean success, String message){
        this.success = success;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public boolean Equals(Response response1, Response response2){
        if (response1.getMessage().equals(response2.getMessage()) && response1.isSuccess() == response2.isSuccess()){
            return true;
        }
        return false;
    }

}
