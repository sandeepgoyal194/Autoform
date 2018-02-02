package com.application.autoform.model.bean;

/**
 * Created by sandeep.g9 on 3/22/2017.
 */

public class Response {
    private String Status;
    private String Error;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        this.Error = error;
    }
}
